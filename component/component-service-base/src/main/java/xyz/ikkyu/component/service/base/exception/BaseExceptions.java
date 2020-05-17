package xyz.ikkyu.component.service.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.ikkyu.common.exception.ExceptionType;

@Getter
@AllArgsConstructor
public enum BaseExceptions implements ExceptionType {

	SERVER_EXCEPTION(900,"服务器异常")

	;



	private int code;
	private String description;
}
