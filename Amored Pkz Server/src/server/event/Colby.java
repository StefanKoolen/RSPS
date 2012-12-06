package server.event;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Handles all scheduled event execution
 * @author Colby
 */
public class Colby {

    public static int currCycle = 0;
    public static long currTime = 0L;

    public Colby() {
    }
    private final LinkedList<Event> events = new LinkedList<Event>();

    public void execute() {
        execute(++currCycle, (currTime = System.currentTimeMillis()));
    }

    /**
     * Run once per cycle after packet processing
     * @param time The current time in milliseconds
     * @param cycle The current server cycle
     */
    public void execute(int cycle, long time) {
        for (int i = 0, l = events.size(); i < l; i++) {
            Event next = events.poll();
            if (next.needsRun(time, cycle)) {
                next.execute0(time, cycle);
                next.execute();
            }
            if (next.willRunAgain()) {
                events.addLast(next);
            }
        }
    }

    public void add(Event e) {
        events.addLast(e);
    }

    /**
     * An event that runs on server cycles
     */
    public static abstract class CycleEvent extends Event {

        public CycleEvent(int initialDelay, int delay, int numRuns) {
            super(currCycle, initialDelay, delay, numRuns);
        }

        @Override
        public abstract void execute();

        @Override
        protected void execute0(long time, int cycle) {
            lastRun = cycle;
        }

        @Override
        public boolean needsRun(long time, int cycle) {
            return cycle - lastRun >= delay;
        }
    }

    /**
     * An event that runs on time
     * NOTE: Can only handle event timings
     * in increments of the servers cycle, all
     * other increments will be rounded.
     */
    public static abstract class TimeEvent extends Event {

        /**
         * Executes as normal but with the default
         * TimeUnit of MILLISECONDS
         */
        public TimeEvent(long initialDelay, long delay, int numRuns) {
            this(initialDelay, delay, numRuns, TimeUnit.MILLISECONDS);
        }

        /**
         *
         * @param initialDelay The initial delay before running
         * @param delay The delay to wait before running
         * @param numRuns How many times to run, -1 for infinite
         * @param unit The TimeUnit for initial and delay
         */
        public TimeEvent(long initialDelay, long delay, int numRuns, TimeUnit unit) {
            super(currTime, TimeUnit.MILLISECONDS.convert(initialDelay, unit),
                    TimeUnit.MILLISECONDS.convert(delay, unit), numRuns);
        }

        @Override
        public abstract void execute();

        @Override
        protected void execute0(long time, int cycle) {
            super.execute0(time, cycle);
            lastRun = time;
        }

        @Override
        protected boolean needsRun(long time, int cycle) {
            return time - lastRun >= delay;
        }
    }

    /**
     * Represents all events
     */
    public static abstract class Event {

        public Event(long time, long initialDelay, long delay, int numRuns) {
            lastRun = time - (delay < initialDelay ? initialDelay : delay);
            numRunsLeft = numRuns;
            this.delay = delay;
        }
        protected int numRunsLeft;
        protected long delay;
        protected long lastRun;

        /**
         * Executes this event
         */
        public abstract void execute();

        /**
         * Backend stuff
         */
        protected void execute0(long time, int cycle) {
            if (numRunsLeft > 0) {
                --numRunsLeft;
            }
        }

        /**
         * Tells if this event will run again
         */
        protected boolean willRunAgain() {
            return numRunsLeft != 0;
        }

        /**
         * Tells if this Event needs to be run
         */
        protected abstract boolean needsRun(long time, int cycle);
    }
}