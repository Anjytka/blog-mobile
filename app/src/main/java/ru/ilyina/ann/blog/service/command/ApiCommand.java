package ru.ilyina.ann.blog.service.command;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Created by anjytka on 29.03.17.
 */

public interface ApiCommand<R> extends Callable<R>, Serializable {}