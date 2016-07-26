package com.eaglesakura.android.property.model;

import java.util.ArrayList;
import java.util.List;

/**
 * GreenDAOの生成ファイル
 */
public class DaoSource {

    /**
     * 生成元のパッケージ名
     */
    public String basePackageName;

    /**
     * 1つのSchemaを定義する
     *
     * 1Schemaにつき1DaoMaster/DaoSessionが生成される
     */
    public static class Schema {
        /**
         * schemaのパッケージ名
         */
        public String packageName;

        /**
         * schema version
         */
        public int version;
    }

    public static class Entity {
        public String name;
    }

    /**
     * カラムデータ
     */
    public static class Column {

    }

    public static class Property {
        /**
         * プロパティ名
         */
        public String name;

        /**
         * 型
         * `int`, `String`, `id`...
         */
        public String type;

        /**
         * `unique`
         * `notNull`
         * `primaryKey`
         * `index`
         */
        public List<String> options = new ArrayList<>();
    }
}
