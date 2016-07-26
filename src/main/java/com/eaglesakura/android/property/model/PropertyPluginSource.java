package com.eaglesakura.android.property.model;

import java.util.List;

/**
 * Text Key-Valueのデフォルト値を保持するJsonModel
 */
public class PropertyPluginSource {
    /**
     * 複数のプロパティを1ファイルで管理する
     */
    public List<Group> groups;

    /**
     * 出力先のファイルパスを指定する
     */
    public String classesOutputPath = "src/main/gen/props";

    /**
     * 設定ファイルの出力パスを指定する
     */
    public String configOutputPath = "src/main/gen/assets/config";

    /**
     * プロパティのパッケージ名
     */
    public String packageName;

    /**
     * 出力されるプロパティ名
     */
    public String configName = "properties.json";

    /**
     * 1つのプロパティを管理する
     */
    public static class Property {
        /**
         * デフォルト値
         */
        public String value;

        /**
         * プロパティ名
         */
        public String name;

        /**
         * 型名, String, int, long等
         */
        public String type;
    }

    /**
     * グルーピング
     */
    public static class Group {
        /**
         * プロパティ一覧
         */
        public List<Property> properties;

        /**
         * グループ名
         */
        public String name;

        public List<Property> getProperties() {
            return properties;
        }

        public void setProperties(List<Property> properties) {
            this.properties = properties;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
