Salt9000
========

Salt9000 is parser of the Salt language used by [PlantUML](plantuml.sourceforge.net) and a translator to PNG
using real Swing widgets.

## Salt9000 - ANTLR parser for the Salt language

Salt9000 is a parser build with an [ANTLR](www.antlr.org) grammar able to parse,
a subset at the moment, of the [Salt](http://plantuml.sourceforge.net/salt.html) language,
a PlantUML extension used to define UI from a textual definition.

You can use Salt9000 in several ways:

  * use it as a stand-alone parser in your applications.
  * write your own visitor (or listener) using `SaltTextVisitor` or `SaltSwingVisitor` as a base.
  * use the `SaltSwingVisitor` to transform a text-based UI definition to set of `JFrame`, `JPanel`, `JTextField`, etc.
  * use the command line interface to render a text-based UI definition to a PNG file with your preferred Swing Look&Feel.

If you want to improve Salt9000:

  * the *Compile Salt9000* launch configuration let you generate default parser, lexer, listener and visitor from
  the `Salt.g4` grammar file.

### TODO

The following are still missing because this is the very first version and I don't need them at the moment:

  * separators (.., ==, ~~, --).
  * vertical tabs.
  * creole syntax: probably I won't never need it and for the integration with PlantUML this part is not required.
  * cell spans.
  * OpenIconic icons.
  * vertical opened menus.
  
### Getting started

  1. clone the project.
  2. import it into eclipse.

From here you can:

  * run the test suite.
  * run *grun Salt9000* launch configuration and visualize parsing result.
  * run *SaltCLI* launch configuration and obtain the PNG file of the specified UI in `Salt9000.s9`.
  * generate examples from command line with `java -jar dist/salt9000.jar --output-dir file-gen examples/*.s9`
  * to use the JGoodies L&F switch (--jg-laf) you should use the complete distribution salt9000-complete.jar .

Probably the most complete example is examples/Salt9000.s9, where this:

    {
    {* File | Edit | Help}
    {
      {/ <b>Ticket | Other tab }
      {Ticket no: | "655321" | Date: | "2014-08-23" }
      {Shop: | ^Las Vegas^ | . | . }
      {() Radio unchecked}
      {(X) Radio checked}
      {[] Check box unchecked}
      {[X] Check box checked}
      {#
        Product | Quantity | Price
        USB pen | 1 | 10 
        DVD | 2 | 1 
         .  | . | . 
      }
      {[New] | [Edit] | [Delete] }
      {Total: | "12.00" }
      {[Save] | [Cancel] }
    }
    }

is translated to this image:

![Salt9000 example](file-gen/Salt9000.png)

### Use Salt9000 as a stand-alone parser

To use Salt9000 as a parser in your project you need to include the salt.jar file in your classpath.  
The class `SaltTest` has examples of how to instantiate and parse a string if you are new to ANTLR.

### Write your own Visitor.

Check the ANTLR documentation about the difference between a Visitor and a Listener, I have chosen to use a Visitor
because some rules do not need to be visited.

### Using the SaltSwingVisitor

The main objective I had when I developed Salt9000 was to be able to generate screenshots from a textual definition,
to be included into the documentation, actually written with Sphinx.

However, probably in the future when Salt9000 will be improved, it's also possible to construct a Swing layout from
its textual definition. See SaltCLI about how to use the SaltSwingVisitor class.

### Command line interface to generate PNG images.

Into the distribution you can find to jars:

  * salt.jar with default features.
  * salt-complete.jar including the jgoodies Look&Feel.

Run java -jar salt.jar to obtain the complete syntax.

There is a launch configuration named *SaltCLI* that parses and translates the `examples/Salt9000.s9` file.

### Developers

I have included a launch configuration to regenerate lexer and parser from Salt.g4
in case you want to experiment with changing the grammar. It's the *Compile Salt9000* launch configuration.

### Distribution

The distribution is compiled with Java 6, however the build.xml file let you generate the jar you need.
