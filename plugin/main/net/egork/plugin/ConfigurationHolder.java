package net.egork.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.*;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class ConfigurationHolder {
	private static ConfigurationHolder instance = new ConfigurationHolder();
	private TaskConfiguration currentTask = null;
	private NavigableMap<String, TaskConfiguration> tasks = new TreeMap<String, TaskConfiguration>();
	private VirtualFile homeDirectory = null;

	public static ConfigurationHolder getInstance() {
		instance.rebuildTaskList(instance.currentTask == null ? "" : instance.currentTask.getTaskID());
		return instance;
	}

	private ConfigurationHolder() {
		VirtualFileManager.getInstance().addVirtualFileListener(new VirtualFileListener() {
			public void propertyChanged(VirtualFilePropertyEvent virtualFilePropertyEvent) {
			}

			public void contentsChanged(VirtualFileEvent virtualFileEvent) {
				String fileName = checkFileEvent(virtualFileEvent);
				if (fileName == null)
					return;
				rebuildTaskList(fileName);
			}

			public void fileCreated(VirtualFileEvent virtualFileEvent) {
				String fileName = checkFileEvent(virtualFileEvent);
				if (fileName == null)
					return;
				rebuildTaskList(fileName);
			}

			public void fileDeleted(VirtualFileEvent virtualFileEvent) {
				String fileName = checkFileEvent(virtualFileEvent);
				if (fileName == null)
					return;
				rebuildTaskList(currentTask == null ? "" : currentTask.getTaskID());
			}

			public void fileMoved(VirtualFileMoveEvent virtualFileMoveEvent) {
			}

			public void fileCopied(VirtualFileCopyEvent virtualFileCopyEvent) {
			}

			public void beforePropertyChange(VirtualFilePropertyEvent virtualFilePropertyEvent) {
			}

			public void beforeContentsChange(VirtualFileEvent virtualFileEvent) {
			}

			public void beforeFileDeletion(VirtualFileEvent virtualFileEvent) {
			}

			public void beforeFileMovement(VirtualFileMoveEvent virtualFileMoveEvent) {
			}
		});
	}

	private String checkFileEvent(VirtualFileEvent virtualFileEvent) {
		if (updateHomeDirectory() == null)
			return null;
		VirtualFile file = virtualFileEvent.getFile();
		if (!"task".equals(file.getExtension()))
			return null;
		if (!file.getParent().equals(homeDirectory))
			return null;
		return file.getNameWithoutExtension();
	}

	private VirtualFile updateHomeDirectory() {
		Project project = Util.getProject();
		if (project == null)
			return homeDirectory = null;
		VirtualFile baseDirectory = project.getBaseDir();
		if (baseDirectory == null)
			return homeDirectory = null;
		homeDirectory = baseDirectory.findChild("main");
		return homeDirectory;
	}

	private void rebuildTaskList(String selected) {
		if (updateHomeDirectory() == null)
			return;
		tasks.clear();
		for (VirtualFile child : homeDirectory.getChildren()) {
			if (!"task".equals(child.getExtension()))
				continue;
			TaskConfiguration configuration = Util.loadConfiguration("main/" + child.getName());
			if (configuration != null)
				tasks.put(configuration.getTaskID(), configuration);
		}
		if (tasks.isEmpty()) {
			currentTask = null;
			return;
		}
		currentTask = tasks.get(selected);
		if (currentTask == null && !tasks.tailMap(selected).isEmpty())
			currentTask = tasks.get(tasks.tailMap(selected).firstKey());
		if (currentTask == null)
			currentTask = tasks.firstEntry().getValue();
	}

	public TaskConfiguration getCurrentTask() {
		return currentTask;
	}

	public TaskConfiguration[] getTasks() {
		return tasks.values().toArray(new TaskConfiguration[tasks.size()]);
	}

	public void setCurrentTask(TaskConfiguration currentTask) {
		this.currentTask = currentTask;
	}
}