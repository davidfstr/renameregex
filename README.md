# renameregex 1.0

Renames all files in the current directory using regular expression patterns.

For example `renameregex '(.*)\.JPG' '$1\.jpg` will rename all files that end with `.JPG` to end with `.jpg`.

Real-world example:

```
[davidf Letters]$ renameregex '(.*?) *\((.*)\)(.*)' '$2 - $1$3'

Letter to Arlene (2010-08-18).JPG => 2010-08-18 - Letter to Arlene.JPG
Letter to Arlene (2010-08-18).rtf => 2010-08-18 - Letter to Arlene.rtf
Letter to Arlene (2011-03-03).jpg => 2011-03-03 - Letter to Arlene.jpg
Letter to Arlene (2011-11-06).rtf => 2011-11-06 - Letter to Arlene.rtf
Letter to Arlene (2012-02-23).rtf => 2012-02-23 - Letter to Arlene.rtf
Letter to Arlene (2012-07-07).rtf => 2012-07-07 - Letter to Arlene.rtf
Letter to Arlene (2012-11-25).rtf => 2012-11-25 - Letter to Arlene.rtf
Letter-Photo (2011-03-03).graffle => 2011-03-03 - Letter-Photo.graffle

Okay? [y/N] y
```

## Requirements

* OS X or Linux
* Java Development Kit (JDK) / **javac**
* **bash**
    * *Only one line needs to be changed to work with other shells.*
* **make**

## Installation

```
$ mkdir -p ~/bin
$ cd ~/bin
$ git clone https://github.com/davidfstr/renameregex.git
      # ...or download this repository to ~/bin/renameregex
$ cd renameregex
$ make
$ echo 'export PATH="$PATH:$HOME/bin/renameregex"' >> ~/.profile
```

## Usage

<tt>**renameregex** *filename_regex* *replacement_pattern*</tt>

Rename all files in the current directory that match the pattern *filename_regex* to a new name based on the *replacement_pattern*.

Before the rename is applied, a preview is printed of what files will be renamed and what they well be renamed to.

### Arguments

* *filename_regex* &mdash; A Perl-compatible regular expression (PCRE), specifically a [Java regular expression], that is used to identify files to rename.

* *replacement_pattern* &mdash; A [Java regex replacement string] used to generate the new filenames. Expressions like `$1` and `$2` can be used to refer to groups matched by *filename_regex*.

[Java regular expression]: http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html

[Java regex replacement string]: http://docs.oracle.com/javase/7/docs/api/java/util/regex/Matcher.html#appendReplacement(java.lang.StringBuffer,%20java.lang.String)

## License

This software is licensed under the [MIT License].

[MIT License]: https://github.com/davidfstr/renameregex/blob/master/LICENSE.txt