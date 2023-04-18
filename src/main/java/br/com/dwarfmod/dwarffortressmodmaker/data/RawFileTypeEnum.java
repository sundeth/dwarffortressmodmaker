/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.data;

/**
 *
 * @author Ander
 */
public enum RawFileTypeEnum {
    
    WORLD("", "https://dwarffortresswiki.org/index.php/World_token", new String[] { }, true),
    BIOME("used to indicate biomes for creatures, plants and entities.", "https://dwarffortresswiki.org/index.php/DF2014:Biome_token", new String[] {}, true),
    MATERIAL_DEFINITION("material definitions (whether for inorganics or those within plants and creatures) as well as in material templates", "https://dwarffortresswiki.org/index.php/DF2014:Material_definition_token", new String[] { "USE_MATERIAL_TEMPLATE" , "LIQUID", "ALL", "IMPLIES_ANIMAL_KILL" }, true),
    
    CE("determine exactly how the poor creature suffering from the syndrome is affected", "https://dwarffortresswiki.org/index.php/DF2014:Syndrome", new String[] { "CE_X", "CE_BRUISING", "CE_REDUCE_PAIN", "CE_ADD_TAG" }, true),
    CE_TRIGGERS("limits the application of a syndrome effect to the given trigger", "https://dwarffortresswiki.org/index.php/DF2014:Syndrome", new String[] { "MOON_PHASE", "ALCOHOLIC" }, true),
    SYNDROME("condition which applies a collection of effects to creatures who contract it", "https://dwarffortresswiki.org/index.php/DF2014:Syndrome", new String[] { "SYNDROME" }, true, CE, CE_TRIGGERS),
    
    BODYGLOSS("performs a simple word substitution on the creature's body parts", "https://dwarffortresswiki.org/index.php/DF2014:Bodygloss", new String[] {}, true),
    BODY("body parts and structures", "https://dwarffortresswiki.org/index.php/DF2014:Body_token", new String[] {}, false, BODYGLOSS),
    BODY_DETAIL_PLAN("similar to CREATURE_VARIATION, but used for defining tokens related to body parts (specifically materials, tissues, the assignment thereof, and body part positions, relative sizes, etc", "https://dwarffortresswiki.org/index.php/DF2014:Body_detail_plan_token", new String[] {}, false),
    
    LABOR("mainly used in custom buildings.", "https://dwarffortresswiki.org/index.php/DF2014:Labor_token", new String[] {}, true),
    BUILDING("custom workshops and smelters", "https://dwarffortresswiki.org/index.php/DF2014:Building_token", new String[] {}, false, LABOR),
    
    DESCRIPTOR_COLOR ("(tag is COLOR) - named colors for use with DESCRIPTOR_PATTERN objects (other purposes unknown)", "https://dwarffortresswiki.org/index.php/DF2014:Descriptor_color_token", new String[] {}, false),
    DESCRIPTOR_PATTERN ("(tag is COLOR_PATTERN) - patterns with color combinations for use with creatures", "https://dwarffortresswiki.org/index.php/DF2014:Descriptor_pattern_token", new String[] {}, false),
    DESCRIPTOR_SHAPE ("(tag is SHAPE) - shapes with descriptions and variations, used for engravings", "https://dwarffortresswiki.org/index.php/DF2014:Descriptor_shape_token", new String[] {}, false),
    
    ETHIC_TYPE("are used in the entity raw files to determine how different civilizations feel about various issues", "https://dwarffortresswiki.org/index.php/DF2014:Ethic", new String[] { "ASSAULT" }, true),
    ETHIC_VALUE("are used in the entity raw files to determine how different civilizations feel about various issues", "https://dwarffortresswiki.org/index.php/DF2014:Ethic", new String[] { "NOT_APPLICABLE" }, true, ETHIC_TYPE),
    POSITION("", "https://dwarffortresswiki.org/index.php/DF2014:Position_token", new String[] { }, true),
    ENTITY("civilization types, with assigned race, language, culture, ethics, and social structure", "https://dwarffortresswiki.org/index.php/DF2014:Entity_token", new String[] {}, false, POSITION, BIOME, ETHIC_TYPE),
    
    CREATURE_GRAPHICS("", "https://dwarffortresswiki.org/index.php/DF2014:Creature_texture_token", new String[] {}, true),
    GRAPHICS("graphic tiles for creatures. These are not found inside the raw/objects folder", "", new String[] {}, false, CREATURE_GRAPHICS),
    TILE_PAGE("defines graphic assets", "", new String[] {}, false),
    PALETTE("defines color palettes to be used with graphics", "", new String[] {}, false),
    
    MANNERISM("give a possibility of a personality quirk being included in each member of a caste", "https://dwarffortresswiki.org/index.php/DF2014:Creature_mannerism_token", new String[] {}, true),
    ATTACK("", "https://dwarffortresswiki.org/index.php/DF2014:Creature_token", new String[] { "ATTACK_SKILL" }, true),
    UNIT_TYPE("", "https://dwarffortresswiki.org/index.php/DF2014:Unit_type_token", new String[] { }, true),
    PERSONALITY_TRAIT(" made up of beliefs, goals, and facets, distinct from attributes and mannerisms", "https://dwarffortresswiki.org/index.php/DF2014:Personality_trait", new String[] { "LAW", "START_A_FAMILY", "LOVE_PROPENSITY Conflicts with ROMANCE" }, true),
    INTERACTION("interaction definitions", "https://dwarffortresswiki.org/index.php/DF2014:Interaction_token", new String[] { "INTERACTION" }, false),
    LAYER_TISSUE("", "https://dwarffortresswiki.org/index.php/DF2014:Tissue_definition_token", new String[] { "SET_LAYER_TISSUE" }, false),
    TISSUE("", "https://dwarffortresswiki.org/index.php/DF2014:Tissue_definition_token", new String[] { "TISSUE_NAME" }, false, LAYER_TISSUE),
    CREATURE_VARIATION("variations that can be applied to creatures (e.g., making them giants, or anthropomorphic). Technically a series of tokens that are added to or removed from the creature (essentially a set of templated changes)", "https://dwarffortresswiki.org/index.php/DF2014:Creature_variation_token", new String[] {}, false, BIOME, SYNDROME, TISSUE, UNIT_TYPE, ATTACK, BODY, BODY_DETAIL_PLAN, MANNERISM),
    CREATURE("creatures", "https://dwarffortresswiki.org/index.php/DF2014:Creature_token", new String[] {"ADOPTS_OWNER", "BABY", "CAN_DO_INTERACTION", "DEMON", "EBO_ITEM", "FANCIFUL", "GAIT", "HABIT", "IMMOBILE", "LAIR", "MAGICAL", "NAME", "ODOR_LEVEL", "PACK_ANIMAL", "RELSIZE", "SAVAGE", "TENDONS", "UBIQUITOUS", "VEGETATION", "WAGON_PULLER"}, false, BIOME, INTERACTION, CREATURE_VARIATION, SYNDROME, TISSUE, UNIT_TYPE, ATTACK, BODY, BODY_DETAIL_PLAN, MANNERISM, MATERIAL_DEFINITION),
    
    NON_LIVING_MATERIAL("inorganic material definitions, generally for stones, gems, and metals, but not with materials attached to plants or creatures", "https://dwarffortresswiki.org/index.php/DF2014:Inorganic_material_definition_token", new String[] {}, true),
    INORGANIC("inorganic material definitions", "https://dwarffortresswiki.org/index.php/DF2014:Material_token", new String[] {}, false, MATERIAL_DEFINITION, NON_LIVING_MATERIAL),
    TEXT_SET("text descriptions", "", new String[] {}, false),
    
    ITEM("items ranging from ammunition to food types, has secondary types for the purposes of [TYPE:ID]", "https://dwarffortresswiki.org/index.php/DF2014:Item_token", new String[] {}, true),
    
    ITEM_AMMO("ammunition for ranged weapons", "https://dwarffortresswiki.org/index.php/DF2014:Ammo_token", new String[] {}, false, ITEM),
    ITEM_ARMOR("body clothing, including armor", "https://dwarffortresswiki.org/index.php/DF2014:Armor_token", new String[] { }, false, ITEM),
    ITEM_FOOD("prepared food definitions", "https://dwarffortresswiki.org/index.php/DF2014:Item_definition_token", new String[] { "LEVEL" }, false, ITEM),
    ITEM_GLOVES("hand clothing, including armor", "https://dwarffortresswiki.org/index.php/DF2014:Armor_token", new String[] {}, false, ITEM),
    ITEM_HELM("head clothing, including armor", "https://dwarffortresswiki.org/index.php/DF2014:Armor_token", new String[] {}, false, ITEM),
    ITEM_INSTRUMENT("instrument definitions", "https://dwarffortresswiki.org/index.php/DF2014:Instrument_token", new String[] {}, false, ITEM),
    ITEM_PANTS("lower body clothing, including armor", "https://dwarffortresswiki.org/index.php/DF2014:Armor_token", new String[] {}, false, ITEM),
    ITEM_SHIELD("shields", "https://dwarffortresswiki.org/index.php/DF2014:Armor_token", new String[] {}, false, ITEM),
    ITEM_SHOES("foot clothing, including armor", "https://dwarffortresswiki.org/index.php/DF2014:Armor_token", new String[] {}, false, ITEM),
    ITEM_SIEGEAMMO("ammunition for siege weapons that ballistae fire", "https://dwarffortresswiki.org/index.php/DF2014:Item_definition_token", new String[] { "CLASS" }, false, ITEM),
    ITEM_TOOL("multi-purpose items that can serve as a weapon, food storage container, etc", "https://dwarffortresswiki.org/index.php/DF2014:Tool_token", new String[] {}, false, ITEM),
    ITEM_TOY("toy definitions", "https://dwarffortresswiki.org/index.php/DF2014:Item_definition_token", new String[] { "HARD_MAT" }, false, ITEM),
    ITEM_TRAPCOMP("components that can be used in weapon traps (two special tags define trapcomps that can be used in other constructions: IS_SCREW and IS_SPIKE)", "https://dwarffortresswiki.org/index.php/DF2014:Trap_component_token", new String[] {}, false),
    ITEM_WEAPON("weapons that are used by soldiers, as well as digging tools", "https://dwarffortresswiki.org/index.php/DF2014:Weapon_token", new String[] {}, false, ITEM),
        
    LANGUAGE("word definitions for the languages used by ENTITY objects\nEntries beginning with [SYMBOL:ID] sort words into symbolic/poetic groups to be referenced by ENTITY preferences.\nEntries beginning with [WORD:ID] define words and their alternate forms (in English).", "https://dwarffortresswiki.org/index.php/DF2014:Language_token", new String[] {}, false),
    MATERIAL_TEMPLATE("definitions of information common to groups of materials (referenced all over the place)", "https://dwarffortresswiki.org/index.php/DF2014:Material_definition_token", new String[] {}, false, SYNDROME),
    
    TREE("tokens used only for trees", "https://dwarffortresswiki.org/index.php/DF2014:Plant_token", new String[] { "TREE" }, true),
    SHRUB("used for non-grass, non-tree plants", "https://dwarffortresswiki.org/index.php/DF2014:Plant_token", new String[] { "SPRING, SUMMER, AUTUMN, WINTER" }, true),
    GRASS("used only for grasses", "https://dwarffortresswiki.org/index.php/DF2014:Plant_token", new String[] { "GRASS" }, true),
    PLANT("definitions of plants, their materials, and their derivatives", "https://dwarffortresswiki.org/index.php/DF2014:Plant_token", new String[] { "NAME", "UNDERGROUND_DEPTH", "GROWTH" }, false, BIOME, TREE, SHRUB, GRASS, SYNDROME, MATERIAL_DEFINITION),
    
    SKILL("", "https://dwarffortresswiki.org/index.php/DF2014:Skill_token", new String[] {}, true),
    REACTION("reactions/custom workshop jobs (turn items into other items through dwarven or adventurer effort)", "https://dwarffortresswiki.org/index.php/DF2014:Reaction", new String[] { "ADVENTURE_MODE_ENABLED" }, false, SKILL, INORGANIC),
    TISSUE_TEMPLATE(" defines templated tissues for use with BODY_DETAIL_PLAN objects or in creatures.", "https://dwarffortresswiki.org/index.php/DF2014:Tissue_definition_token", new String[] { "skin:NP" }, false),
    
    MUSIC("defines music files", "", new String[] {}, false),
    SOUND("defines game sound files", "", new String[] {}, false);
    
    private final String description;
    private final RawFileTypeEnum[] related;
    private final boolean graphics;
    private final String url;
    private final String[] titles;
    private final boolean subType;
    
    private RawFileTypeEnum(final String description, final String url, final String[] titles, final boolean subType, final RawFileTypeEnum...related) {
        this.related = related;
        this.description = description;
        this.graphics = this.name().equals("GRAPHICS");
        this.url = url;
        this.titles = titles;
        this.subType = subType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public RawFileTypeEnum[] getRelated() {
        return related;
    }
    
    public boolean isGraphics() {
        return graphics;
    }
    
    public String getUrl() {
        return url;
    }

    public String[] getTitles() {
        return titles;
    }

    public boolean isSubType() {
        return subType;
    }
}
