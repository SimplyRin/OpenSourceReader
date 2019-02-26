package net.simplyrin.opensourcereader;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by SimplyRin on 2019/02/20.
 *
 * Copyright (c) 2019 SimplyRin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class OpenSourceReader {

	private String repositoryName;
	private String author = "SimplyRin";

	private List<Item> items = new ArrayList<>();

	public OpenSourceReader(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public void check() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/" + this.author + "/" + this.repositoryName + "/master/README.md");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			Scanner scanner = new Scanner(connection.getInputStream());

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("**") || line.endsWith("**")) {
					String name = line.split(Pattern.quote("["))[1].split(" | ")[0];
					String lisence = line.split(Pattern.quote("]"))[0].split(Pattern.quote(" | "))[1];
					String lUrl = line.split(Pattern.quote("]("))[1].split(Pattern.quote(")**"))[0];

					this.items.add(new Item(name, lisence, lUrl));
				}
			}

			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public class Item {

		private String name;
		private String lisence;
		private String url;

		public Item(String name, String lisence, String url) {
			this.name = name;
			this.lisence = lisence;
			this.url = url;
		}

		public String getName() {
			return this.name;
		}

		public String getLisence() {
			return this.lisence;
		}

		public String getUrl() {
			return this.url;
		}

	}

}
