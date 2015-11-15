package org.rapidoid.widget;

/*
 * #%L
 * rapidoid-gui
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski and contributors
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

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.ctx.Ctxs;
import org.rapidoid.html.TagWidget;
import org.rapidoid.html.impl.TagRenderer;
import org.rapidoid.http.Req;
import org.rapidoid.http.fast.ReqImpl;
import org.rapidoid.mime.MediaType;
import org.rapidoid.test.TestCommons;
import org.rapidoid.var.Var;

@Authors("Nikolche Mihajlovski")
@Since("2.0.0")
public class WidgetTestCommons extends TestCommons {

	@SuppressWarnings({ "unchecked" })
	protected static final Map<Integer, Object> NO_CHANGES = Collections.EMPTY_MAP;

	@Before
	public void init() {
		Ctxs.open("test-widget"); // open context for each test
		Req x = setupMockExchange();
	}

	@After
	public void cleanup() {
		Ctxs.close(); // clean-up the context for each test
	}

	protected void print(Object content) {
		Req x = setupMockExchange();

		content = preprocess(content, x);

		String html = TagRenderer.get().toHTML(content, x);
		notNull(html);
		System.out.println(html);
	}

	@SuppressWarnings("unchecked")
	private Object preprocess(Object content, Req x) {
		if (content instanceof TagWidget<?>) {
			TagWidget<Req> widget = (TagWidget<Req>) content;
			content = widget.render(x);
			if (content == null) {
				return null;
			}
		}

		return content;
	}

	protected void has(Object content, String... containingTexts) {
		Req x = setupMockExchange();

		content = preprocess(content, x);

		String html = TagRenderer.get().toHTML(content, x);
		notNull(html);

		for (String text : containingTexts) {
			isTrue(html.contains(text));
		}
	}

	protected void hasRegex(Object content, String... containingRegexes) {
		Req x = setupMockExchange();

		content = preprocess(content, x);

		String html = TagRenderer.get().toHTML(content, x);

		for (String regex : containingRegexes) {
			isTrue(Pattern.compile(regex).matcher(html).find());
		}
	}

	protected void eq(Var<?> var, Object value) {
		eq(var.get(), value);
	}

	@SuppressWarnings("unchecked")
	protected static Req setupMockExchange() {

		Req x = new ReqImpl(null, null, false, "GET", "/", "/", "".getBytes(), Collections.EMPTY_MAP,
				Collections.EMPTY_MAP, Collections.EMPTY_MAP, Collections.EMPTY_MAP, Collections.EMPTY_MAP,
				MediaType.HTML_UTF_8);

		Ctxs.ctx().setExchange(x);
		return x;
	}

}