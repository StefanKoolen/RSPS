package server.task;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import server.task.TaskFactory.Task;

public class TaskManager {

	/*
	 * Note: The pool size _must_ remain 1 to keep synchronization proper.
	 */
	private static ScheduledThreadPoolExecutor scheduledExecutor = (ScheduledThreadPoolExecutor) Executors
			.newScheduledThreadPool(1);

	public static Future<?> registerClientTask(Task task, int delay) {
		Future<?> taskFuture = TaskManager.scheduledExecutor.schedule(task,
				delay, TimeUnit.SECONDS);
		if (!task.isGlobal())
			task.getClient().setCurrentTask(taskFuture);
		return taskFuture;
	}

	public static Future<?> registerDelayedTask(Runnable task, int delay,
			TimeUnit unit) {
		return TaskManager.scheduledExecutor.schedule(task, delay, unit);
	}

	public static Future<?> scheduleTask(Runnable task, int delay, int rate,
			TimeUnit unit) {
		return TaskManager.scheduledExecutor.scheduleWithFixedDelay(task,
				delay, rate, unit);
	}

}
