package xyz.ikkyu.component.service.base.event;

@FunctionalInterface
public interface Executable {

	/**
	 * 回调代码块
	 */
	void exec();

}