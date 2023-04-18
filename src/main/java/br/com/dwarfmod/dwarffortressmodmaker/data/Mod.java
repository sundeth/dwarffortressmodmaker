package br.com.dwarfmod.dwarffortressmodmaker.data;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Ander
 */
@Data
@Builder
public class Mod implements Comparable<Mod> {
    private String id;
    private String numericVersion;
    private String displayedVersion;
    private String earliestCompatibleNumericVersion;
    private String earliestCompatibleDisplayedVersion;
    private String author;
    private String name;
    private String description;
    private Set<String> requiresIdBeforeMe;
    private String steamTitle;
    private Set<String> steamTags;
    private String steamChangelog;
    private String steamFileId;
    
    private String modPath;
    private ModType type;
    private Set<RawFile> objects;
    private Set<RawFile> graphics;
    
    @Override
    public String toString() {
        return "<html><header></header><body><font color=" + (this.type == ModType.VANILLA ? "'black'" : (this.type == ModType.MODMAKER ? "'#3300cc'" : "'#3b8040'")) + ">" + this.name + "</font></body></html>";
    }

    @Override
    public int compareTo(Mod o) {
        return this.getId().compareTo(o.getId());
    }
}
