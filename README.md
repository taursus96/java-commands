# java-commands

It's a command parser written in java. It takes a command string and return a command object. I know I could just use regex and do it 10 times faster but I wanted to do it manually and do some TDD.

Usage
===

create a command class

```java
public class PrintCommand extends Command {
    
    @Override
    public void exec() {
        System.out.println(this.params.get("s"));
    }
}
```
setup commands repository

```java
ICommandsRepository commandsRepository = new CommandsRepository();
commandsRepository.addCommand("print", PrintCommand.class);
```

parse and execute command

```java
ICommand command = CommandParser.getInstance().parse("print -s \"string to be printed\"", commandsRepository);
command.exec();
```
