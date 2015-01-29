# pack200-maven-plugin
[![Build Status](https://travis-ci.org/arhs/pack200-maven-plugin.svg)](https://travis-ci.org/arhs/pack200-maven-plugin) for Travis CI passing

Plugin to use pack200 and unpack200 command from a maven project.

## Usage

Add the following plugin to your pom.xml file:

	<groupId>com.arhs.maven.plugins</groupId>
	<artifactId>pack200-maven-plugin</artifactId>

## Example

The example below allows to check whether the JAR file can be compressed and then create a compressed file by taking the default name:

    <plugin>
        <groupId>com.arhs.maven.plugins</groupId>
        <artifactId>pack200-maven-plugin</artifactId>
        <executions>
            <execution>
                <id>repack</id>
                <phase>package</phase>
                <goals>
                    <goal>repack</goal>
                </goals>
            </execution>
            <execution>
                <id>pack</id>
                <phase>verify</phase>
                <goals>
                    <goal>pack</goal>
                </goals>
            </execution>
        </executions>
    </plugin>

## Copyright and license

> The MIT License (MIT)
>
> Copyright (c) 2014 ARHS Developments SA
>
> Permission is hereby granted, free of charge, to any person obtaining a copy of
> this software and associated documentation files (the "Software"), to deal in
> the Software without restriction, including without limitation the rights to
> use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
> the Software, and to permit persons to whom the Software is furnished to do so,
> subject to the following conditions:
>
> The above copyright notice and this permission notice shall be included in all
> copies or substantial portions of the Software.
>
> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
> IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
> FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
> COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
> IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
> CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
