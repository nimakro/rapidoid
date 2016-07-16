package org.rapidoid.http.customize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.rapidoid.RapidoidThing;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.config.Config;
import org.rapidoid.ctx.Ctx;
import org.rapidoid.ctx.Ctxs;
import org.rapidoid.http.Req;
import org.rapidoid.setup.My;
import org.rapidoid.u.U;

/*
 * #%L
 * rapidoid-http-fast
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

@Authors("Nikolche Mihajlovski")
@Since("5.1.0")
public class Customization extends RapidoidThing {

	private final String name;
	private final Customization defaults;
	private final Config appConfig;
	private final Config serverConfig;

	private volatile String[] staticFilesPath;

	private volatile String[] templatesPath;

	private volatile ErrorHandler errorHandler;

	private volatile ViewRenderer viewRenderer;

	private volatile PageRenderer pageRenderer;

	private volatile JsonResponseRenderer jsonResponseRenderer;

	private volatile JsonRequestBodyParser jsonRequestBodyParser;

	private volatile BeanParameterFactory beanParameterFactory;

	private volatile LoginProvider loginProvider;

	private volatile RolesProvider rolesProvider;

	private volatile BeanValidator validator;

	private volatile ObjectMapper jackson;

	private volatile EntityManagerProvider entityManagerProvider;

	private volatile EntityManagerFactoryProvider entityManagerFactoryProvider;

	public Customization(String name, Customization defaults, Config appConfig, Config serverConfig) {
		this.name = name;
		this.defaults = defaults;
		this.appConfig = appConfig;
		this.serverConfig = serverConfig;

		reset();
	}

	public synchronized void reset() {
		staticFilesPath = null;
		templatesPath = null;
		errorHandler = null;
		viewRenderer = null;
		pageRenderer = null;
		jsonResponseRenderer = null;
		beanParameterFactory = null;
		loginProvider = null;
		rolesProvider = null;
		validator = null;
		jackson = null;
		entityManagerProvider = null;
		entityManagerFactoryProvider = null;
	}

	public static Customization of(Req req) {
		Ctx ctx = Ctxs.get();
		Req rr = ctx != null ? (Req) ctx.exchange() : null;
		U.must(req == rr, "The customization request doesn't match the context request!");
		return req != null ? req.custom() : My.custom();
	}

	public static Customization current() {
		Ctx ctx = Ctxs.get();
		return of(ctx != null ? (Req) ctx.exchange() : null);
	}

	public String name() {
		return name;
	}

	public Customization defaults() {
		return defaults;
	}

	public Config appConfig() {
		return appConfig;
	}

	public Config serverConfig() {
		return serverConfig;
	}

	public String[] staticFilesPath() {
		return staticFilesPath != null || defaults == null ? staticFilesPath : defaults.staticFilesPath();
	}

	public Customization staticFilesPath(String... staticFilesPath) {
		this.staticFilesPath = staticFilesPath;
		return this;
	}

	public String[] templatesPath() {
		return templatesPath != null || defaults == null ? templatesPath : defaults.templatesPath();
	}

	public Customization templatesPath(String... templatesPath) {
		this.templatesPath = templatesPath;
		return this;
	}

	public ErrorHandler errorHandler() {
		return errorHandler != null || defaults == null ? errorHandler : defaults.errorHandler();
	}

	public Customization errorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		return this;
	}

	public ViewRenderer viewRenderer() {
		return viewRenderer != null || defaults == null ? viewRenderer : defaults.viewRenderer();
	}

	public Customization viewRenderer(ViewRenderer viewRenderer) {
		this.viewRenderer = viewRenderer;
		return this;
	}

	public PageRenderer pageRenderer() {
		return pageRenderer != null || defaults == null ? pageRenderer : defaults.pageRenderer();
	}

	public Customization pageRenderer(PageRenderer pageRenderer) {
		this.pageRenderer = pageRenderer;
		return this;
	}

	public JsonResponseRenderer jsonResponseRenderer() {
		return jsonResponseRenderer != null || defaults == null ? jsonResponseRenderer : defaults.jsonResponseRenderer();
	}

	public Customization jsonResponseRenderer(JsonResponseRenderer jsonResponseRenderer) {
		this.jsonResponseRenderer = jsonResponseRenderer;
		return this;
	}

	public BeanParameterFactory beanParameterFactory() {
		return beanParameterFactory != null || defaults == null ? beanParameterFactory : defaults.beanParameterFactory();
	}

	public Customization beanParameterFactory(BeanParameterFactory beanParameterFactory) {
		this.beanParameterFactory = beanParameterFactory;
		return this;
	}

	public LoginProvider loginProvider() {
		return loginProvider != null || defaults == null ? loginProvider : defaults.loginProvider();
	}

	public Customization loginProvider(LoginProvider loginProvider) {
		this.loginProvider = loginProvider;
		return this;
	}

	public RolesProvider rolesProvider() {
		return rolesProvider != null || defaults == null ? rolesProvider : defaults.rolesProvider();
	}

	public Customization rolesProvider(RolesProvider rolesProvider) {
		this.rolesProvider = rolesProvider;
		return this;
	}

	public BeanValidator validator() {
		return validator != null || defaults == null ? validator : defaults.validator();
	}

	public Customization validator(BeanValidator validator) {
		this.validator = validator;
		return this;
	}

	public ObjectMapper jackson() {
		return jackson != null || defaults == null ? jackson : defaults.jackson();
	}

	public Customization jackson(ObjectMapper jackson) {
		this.jackson = jackson;
		return this;
	}

	public EntityManagerProvider entityManagerProvider() {
		return entityManagerProvider != null || defaults == null ? entityManagerProvider : defaults.entityManagerProvider();
	}

	public Customization entityManagerProvider(EntityManagerProvider entityManagerProvider) {
		this.entityManagerProvider = entityManagerProvider;
		return this;
	}

	public EntityManagerFactoryProvider entityManagerFactoryProvider() {
		return entityManagerFactoryProvider != null || defaults == null ? entityManagerFactoryProvider : defaults.entityManagerFactoryProvider();
	}

	public Customization entityManagerFactoryProvider(EntityManagerFactoryProvider entityManagerFactoryProvider) {
		this.entityManagerFactoryProvider = entityManagerFactoryProvider;
		return this;
	}

	public JsonRequestBodyParser jsonRequestBodyParser() {
		return jsonRequestBodyParser != null || defaults == null ? jsonRequestBodyParser : defaults.jsonRequestBodyParser();
	}

	public Customization jsonRequestBodyParser(JsonRequestBodyParser jsonRequestBodyParser) {
		this.jsonRequestBodyParser = jsonRequestBodyParser;
		return this;
	}
}
