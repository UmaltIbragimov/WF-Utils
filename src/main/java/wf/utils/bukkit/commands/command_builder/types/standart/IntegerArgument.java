package wf.utils.bukkit.commands.command_builder.types.standart;

import org.bukkit.entity.Player;
import wf.utils.bukkit.commands.command_builder.types.ArgumentType;
import wf.utils.java.values.TypeUtils;

import java.util.Arrays;
import java.util.List;

public class IntegerArgument implements ArgumentType {

    @Override
    public String getMessage() {
        return "This argument is not valid, enter an number(integer)!";
    }

    @Override
    public String getMessageCode() {
        return "INTEGER_ARGUMENT_WRONG";
    }

    @Override
    public String getName() {
        return "integer";
    }

    @Override
    public boolean isIt(String argument) {
        return TypeUtils.isInteger(argument);
    }

    @Override
    public Object get(String argument) {
        return Integer.parseInt(argument);
    }

    @Override
    public List<String> tabulation(Player player, String arg) {
        return Arrays.asList("0", "1", "5", "10");
    }
}
