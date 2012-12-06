package server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.python.core.Py;
import org.python.core.PyException;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import server.util.log.Logger;

public class ScriptManager {

	public static PythonInterpreter python = new PythonInterpreter();
	private static int scriptsLoaded = 0;

	public static PyObject getVariable(String variable) {
		try {
			return ScriptManager.python.get(variable);
		} catch (PyException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object callFunc(Class<?> c, String funcName, Object... binds) {
		try {
			PyObject obj = ScriptManager.python.get(funcName);
			if (obj != null && obj instanceof PyFunction) {
				PyFunction func = (PyFunction) obj;
				PyObject[] objects = new PyObject[binds.length];
				for (int i = 0; i < binds.length; i++) {
					Object bind = binds[i];
					objects[i] = Py.java2py(bind);
				}
				return func.__call__(objects).__tojava__(c);
			} else
				return null;
		} catch (PyException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean callFunc(String funcName, Object... binds) {
		try {
			PyObject obj = ScriptManager.python.get(funcName);
			if (obj != null && obj instanceof PyFunction) {
				PyFunction func = (PyFunction) obj;
				PyObject[] objects = new PyObject[binds.length];
				for (int i = 0; i < binds.length; i++) {
					Object bind = binds[i];
					objects[i] = Py.java2py(bind);
				}
				func.__call__(objects);
				return true;
			} else
				return false;
		} catch (PyException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static void loadScripts() throws IOException {
		System.out.println("Loading scripts...");
		ScriptManager.python.cleanup();
		File scriptDir = new File("./Data/scripts/");
		if (scriptDir.isDirectory() && !scriptDir.getName().startsWith(".")) {
			File[] children = scriptDir.listFiles();
			for (File child : children)
				if (child.isFile()) {
					if (child.getName().endsWith(".py")) {
						System.out.println("\tLoading script: "
								+ child.getPath());
						ScriptManager.python
								.execfile(new FileInputStream(child));
						ScriptManager.scriptsLoaded++;
					}
				} else
					ScriptManager.recurse(child.getPath());
		}
		System.out.println("Loaded " + ScriptManager.scriptsLoaded
				+ " scripts!");
		ScriptManager.scriptsLoaded = 0;
	}

	private static void recurse(String dir) throws IOException {
		File scriptDir = new File(dir);
		if (scriptDir.isDirectory() && !scriptDir.getName().startsWith(".")) {
			File[] children = scriptDir.listFiles();
			for (File child : children)
				if (child.isFile()) {
					if (child.getName().endsWith(".py")) {
						System.out.println("\tLoading script: \r"
								+ child.getPath());
						ScriptManager.python
								.execfile(new FileInputStream(child));
						ScriptManager.scriptsLoaded++;
					}
				} else
					ScriptManager.recurse(child.getPath());
		}
	}

	static {
		ScriptManager.python.setOut(new Logger(System.out));
		ScriptManager.python.setErr(new Logger(System.err));
	}
}
