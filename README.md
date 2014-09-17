```
███╗   ██╗██╗     ██████╗       ███████╗ ██████╗ ██████╗ ██████╗ ███████╗
████╗  ██║██║     ██╔══██╗      ██╔════╝██╔════╝██╔═══██╗██╔══██╗██╔════╝
██╔██╗ ██║██║     ██████╔╝█████╗███████╗██║     ██║   ██║██████╔╝█████╗
██║╚██╗██║██║     ██╔═══╝ ╚════╝╚════██║██║     ██║   ██║██╔══██╗██╔══╝
██║ ╚████║███████╗██║           ███████║╚██████╗╚██████╔╝██║  ██║███████╗
╚═╝  ╚═══╝╚══════╝╚═╝           ╚══════╝ ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝
```

An automation tool to get dev score for CMPT413 assignments

---

## Quickstart

### Requirements

The following software components are required to be pre-installed in order to develop for this project:

* [maven](http://maven.apache.org/) `v3.0.0+`
* [java](http://www.oracle.com/technetwork/java/javase/overview/index.html)

### Installing Dependencies

Here is one method of installing all the necessary dependencies on a Mac via Homebrew:

    $ brew update
    $ brew install maven

    # test #
    $ mvn -v

### Build a release

    $ cd /path/to/nlp-score
    $ mvn clean package

### Run the code
    $ cd /path/to/nlp-score
    $ mvn clean package
    $ java -cp target/score-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.lindt.Score <gmail-account> <gmail-passwd> <absolute-path-to-upload-file>
