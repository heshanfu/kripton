/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor;

import static com.google.common.truth.Truth.assertAbout;
import static com.abubusoft.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.abubusoft.testing.compile.JavaSourcesSubjectFactory.javaSources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.tools.JavaFileObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.example01.Channel;
import com.abubusoft.kripton.example01.ChannelMessage;
import com.abubusoft.kripton.example01.DaoChannel;
import com.abubusoft.kripton.example01.DaoChannelMessage;
import com.abubusoft.kripton.processor.test01.Dummy01DataSource;
import com.google.common.truth.FailureStrategy;
import com.google.common.truth.TestVerb;
import com.abubusoft.testing.compile.CompileTester.CompilationResultsConsumer;
import com.abubusoft.testing.compile.CompileTester.GenerationClause;
import com.abubusoft.testing.compile.CompileTester.SuccessfulCompilationClause;
import com.abubusoft.testing.compile.JavaFileObjects;

@RunWith(JUnit4.class)
public class BindDataSourceProcessorTest extends BaseProcessorTest {

	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		//buildDataSourceProcessorTest(Dummy01DataSource.class, DaoChannelMessage.class, ChannelMessage.class);
	}

	@Test
	public void testMultiple() throws IOException {
	/*	
		final List<JavaFileObject> sourcesPhase2=new ArrayList<JavaFileObject>();
		
		final List<JavaFileObject> sourcesPhase1=sources(
				Dummy01DataSource.class,
				ChannelMessage.class, Channel.class,
				DaoChannelMessage.class, DaoChannel.class
		);
		
		//@formatter:off
		SuccessfulCompilationClause result1 = assertAbout(javaSources()).that(
				sourcesPhase1).processedWith(new BindDataSourceProcessor()).compilesWithoutError();
		//@formatter:on
		GenerationClause<SuccessfulCompilationClause> resultPhase1 = result1.and().generatesSources();

		
		resultPhase1.forAllOfWhich(new CompilationResultsConsumer() {

			@Override
			public void accept(Map<String, JavaFileObject> t) {				
				for (Entry<String, JavaFileObject> item : t.entrySet()) {
					logger.info("item " + item.getKey());
					try {
						sourcesPhase2.add(item.getValue());
						logger.info("-------\n" + getStringFromInputStream(item.getValue().openInputStream()));
						writeGeneratedFile(item.getValue());
						//assertAbout(javaSource()).that(item.getValue()).compilesWithoutError();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});*/
				
	}

	@Test
	public void test02() throws IOException {
		Path path = Paths.get("src/test/java/", UserIdentity.class.getCanonicalName().replace(".", Character.toString(File.separatorChar)) + ".java");

		byte[] buffer = Files.readAllBytes(path.toAbsolutePath());

		JavaFileObject source = JavaFileObjects.forSourceLines(UserIdentity.class.getCanonicalName(), new String(buffer));
		// assertAbout(javaSource).that()
		SuccessfulCompilationClause result = assertAbout(javaSource()).that(source).processedWith(new BindDataSourceProcessor()).compilesWithoutError();

		GenerationClause<SuccessfulCompilationClause> sources = result.and().generatesSources();

		sources.forAllOfWhich(new CompilationResultsConsumer() {

			@Override
			public void accept(Map<String, JavaFileObject> t) {

				for (Entry<String, JavaFileObject> item : t.entrySet()) {
					logger.info("item " + item.getKey());
					try {
						logger.info("-------\n" + getStringFromInputStream(item.getValue().openInputStream()));
						assertAbout(javaSource()).that(item.getValue()).compilesWithoutError();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});

	}

	private static final TestVerb VERIFY = new TestVerb(new FailureStrategy() {
		@Override
		public void fail(String message) {
			throw new RuntimeException(message);
		}
	});

	@Test
	public void compilesWithoutWarnings_failsWithWarnings() throws IOException {
		/*
		 * Path path = Paths.get("src/test/java/", EntityBean.class.getCanonicalName().replace(".", Character.toString(File.separatorChar)) + ".java");
		 * 
		 * byte[] buffer = Files.readAllBytes(path.toAbsolutePath());
		 * 
		 * JavaFileObject source = JavaFileObjects.forSourceLines(EntityBean.class.getCanonicalName(), new String(buffer));
		 * 
		 * VERIFY.about(javaSource()).that(source).processedWith(new BundleTypeProcessor()).compilesWithoutError();
		 */

	}
}
