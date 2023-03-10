package wf.utils.bukkit.config.language;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import wf.utils.bukkit.config.BukkitConfig;
import wf.utils.java.file.utils.ResourceUtils;
import wf.utils.java.file.yamlconfiguration.configuration.ConfigDefaultValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GeneralLanguage implements Language {



    private static List<String> availableLanguages;
    private BukkitConfig languageConfig;
    private BukkitConfig optionsConfig;
    private String toPath;
    private ConfigDefaultValue[] defaultValues;
    private String selectedLanguage;

    public GeneralLanguage(Plugin plugin, String resourcePath, String toPath, ConfigDefaultValue... defaultValues) {
        this.toPath = toPath;
        this.defaultValues = defaultValues;
        optionsConfig = new BukkitConfig(plugin,toPath + File.separator + "options");
        availableLanguages = copyAllConfigs(plugin, resourcePath, toPath);

        if(optionsConfig.contains("general_language")){
            String generalLanguage = optionsConfig.getString("general_language");
            if(availableLanguages.contains(generalLanguage)){
                selectLanguage(plugin, generalLanguage);
            }else{
                generalLanguage = availableLanguages.contains("en") ? "en" : availableLanguages.get(0);
                selectLanguage(plugin, generalLanguage);
                optionsConfig.set("general_language", generalLanguage);
            }
            selectedLanguage = generalLanguage;
        }else{
            String generalLanguage = availableLanguages.contains("en") ? "en" : availableLanguages.get(0);
            selectLanguage(plugin, generalLanguage);
            selectedLanguage = generalLanguage;
        }


    }

   private List<String> copyAllConfigs(Plugin plugin, String resourcePath, String toPath){
       List<String> files = ResourceUtils.getResourceFiles(resourcePath);
       List<String> existingFiles = getExistingConfigs(toPath);
       if(files.isEmpty() && existingFiles.isEmpty()){
           languageConfig = new BukkitConfig(plugin, toPath + File.separator + "en.yml",false, defaultValues);
           return Arrays.asList("en");
       }else{
           for(String name : files){
               if(existingFiles.contains(name)) continue;
               ResourceUtils.copyFromResource(resourcePath + File.separator + name,toPath + File.separator + name,false);
           }
       }
       files.addAll(existingFiles);
       return files;
    }

    private List<String> getExistingConfigs(String toPath){
        File folder = new File(toPath);
        List<String> fileNames = Arrays.asList(new File(toPath).list());
        if(fileNames.contains("options.yml")) fileNames.remove("options.yml");
        return fileNames.stream().map((f) -> {return f.split("\\.")[0];}).collect(Collectors.toList());
    }

    public void selectLanguage(Plugin plugin, String lang){
        languageConfig = new BukkitConfig(plugin, toPath + File.separator + lang);
        optionsConfig.set("general_language", lang);
        selectedLanguage = lang;
    }

    public MessageReceiver getMessageReceiver(){
        return MessageReceiverBuilder.create(languageConfig.getConfig(), selectedLanguage);
    }

    public String mess(String path) {
        String mess = languageConfig.getString(path);
        return mess == null ? ChatColor.WHITE + "Message \"" + path + "\" not found" :
                ChatColor.translateAlternateColorCodes('&', mess);
    }

    public String mess(String path, boolean colorTranslate) {
        String mess = languageConfig.getString(path);
        return mess == null ? ChatColor.WHITE + "Message \"" + path + "\" not found" :
                (colorTranslate ? ChatColor.translateAlternateColorCodes('&', mess) : mess);
    }

    public String mess(String path, char colorChar) {
        String mess = languageConfig.getString(path);
        return mess == null ? ChatColor.WHITE + "Message \"" + path + "\" not found" :
                ChatColor.translateAlternateColorCodes(colorChar, mess);
    }

    @Override
    public BukkitConfig getConfig() {
        return null;
    }
}
