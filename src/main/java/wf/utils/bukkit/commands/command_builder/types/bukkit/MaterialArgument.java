package wf.utils.bukkit.commands.command_builder.types.bukkit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import wf.utils.bukkit.commands.command_builder.types.ArgumentType;

import java.util.ArrayList;
import java.util.List;

public class MaterialArgument implements ArgumentType {

    @Override
    public String getMessage() {
        return "This argument is not valid, enter minecraft material name!";
    }

    @Override
    public String getMessageCode() {
        return "MATERIAL_ARGUMENT_WRONG";
    }

    @Override
    public String getName() {
        return "item";
    }

    @Override
    public boolean isIt(String argument) {
        return Material.getMaterial(argument.toUpperCase()) != null;
    }

    @Override
    public Object get(String argument) {
        return Material.getMaterial(argument.toUpperCase());
    }

    @Override
    public List<String> tabulation(Player player, String arg) {
        return getContainedMaterials(arg);
    }



    private List<String> getAllMaterials() {
        List<String> list = new ArrayList<String>();
        for(Material mat : Material.values()) {
            list.add(mat.name().toLowerCase());
        }

        return list;
    }

    private List<String> getContainedMaterials(String material) {
        if(material.isEmpty()) return getAllMaterials();
        List<String> list = new ArrayList<String>();
        for(String mat : getAllMaterials()) {
            if(mat.contains(material.toLowerCase())) {
                list.add(mat);
            }
        }
        return list;
    }


}
