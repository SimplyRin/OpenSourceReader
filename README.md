# OpenSourceReader
自分で使う用

`README.md` の下に書いてある `Open Source Lisence` 一覧を取得する物

テンプレート: `**・[<Project> | <Lisence>](<URL>)**`

# Method 
`Item#getName()`: Repository name (e.g, OpenSourceReader)

`Item#getLisence()`: Repository lisence (e.g, MIT License)

`Item#getUrl()`: Repository lisence url (e.g, https://github.com/SimplyRin/OpenSourceReader/blob/master/LICENSE)

# Usage
```Java
OpenSourceReader osReader = new OpenSourceReader("<Repository Name>");
osReader.check();

for (Item item : osReader.getItems()) {
	System.out.println(item.getName() + " | " + item.getLisence() + " | " + item.getUrl());
}
```
