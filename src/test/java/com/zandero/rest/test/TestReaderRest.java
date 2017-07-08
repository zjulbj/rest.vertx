package com.zandero.rest.test;

import com.zandero.rest.annotation.RequestReader;
import com.zandero.rest.reader.CustomBodyReader;
import com.zandero.utils.StringUtils;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Path("/read")
public class TestReaderRest {

	@POST
	@Path("/custom")
	@RequestReader(CustomBodyReader.class) // use custom reader to convert body to list of String
	public String getWords(List<String> words) {

		return getString(words);
	}

	@POST
	@Path("/registered")
	public String getWords2(List<String> words) { // manually assign reader ....

		return getString(words);
	}

	private String getString(List<String> words) {

		Set<String> unique = new HashSet<>();
		for (String word: words) {
			unique.add(word.toLowerCase());
		}

		// return sorted list of words
		Object[] array = unique.toArray();
		Arrays.sort(array);

		return StringUtils.join(array, ",");
	}

	/*@POST
	@Path("/jacksonMapper")
	@RequestReader(JsonBodyReader.class)
	public String readWithCustomJacksonMapper(String input) {


		return null;
	}*/

/*
	@POST
	@Path("/invalidReader")
	@RequestReader(IntegerBodyReader.class)
	public Boolean getInt(Boolean number) { // manually assign reader not suitable for provided argument type ....

		return number;
	}*/
}
