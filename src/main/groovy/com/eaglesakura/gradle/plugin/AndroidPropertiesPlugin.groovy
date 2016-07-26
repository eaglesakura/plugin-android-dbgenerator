package com.eaglesakura.gradle.plugin

import com.eaglesakura.android.property.model.PropertyPluginSource
import com.eaglesakura.android.property.model.PropertySource;
import com.eaglesakura.gradle.android.property.PropClassGenerator2
import com.eaglesakura.json.JSON
import com.eaglesakura.tool.log.Logger
import com.eaglesakura.util.IOUtil
import org.gradle.api.DefaultTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

/**
 * Androidのプロパティリストを出力するPlugin
 */
public class AndroidPropertiesPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.extensions.create("props", AndroidPropertiesPlugin.DSL);

        // 規定のタスクを追加
        project.task('androidGenerateProperties', type: PropClassGeneratorImpl)
    }

    public static class PropClassGeneratorImpl extends DefaultTask {

        /**
         * 全てのgeneratorを実行する
         */
        @TaskAction
        void build() {
            // クラスを生成
            for (PropClassGenerator2 gen : project.props.generators) {
                gen.outDirectory = project.file(gen.classesOutputPath)
                gen.build();
            }

            // configに一括変換
            for (PropertyPluginSource src : project.props.sources) {
                // テキストに変換 -> 再度Modelにすることで強制的に変換をかける
                PropertySource model = new PropertySource();
                model.groups = [];
                model.groups += JSON.decode(JSON.encode(src.groups), PropertySource.Group[].class);

                File propFile = new File(IOUtil.mkdirs(project.file(src.configOutputPath)), src.configName);
                Logger.out("Generate Config[${propFile.absolutePath}]")
                propFile.write(JSON.encode(model));
            }
        }
    }

    private static PropertyPluginSource loadSource(File file) {
        return JSON.decode(file.text, PropertyPluginSource.class);
    }

    public static class DSL {
        List<PropClassGenerator2> generators = [];
        List<PropertyPluginSource> sources = [];

        /**
         * ジェネレータを追加する
         * @param source
         * @param packageName
         */
        void property(File source) {
            PropertyPluginSource src = loadSource(source);
            for (def group : src.groups) {
                PropClassGenerator2 gen = new PropClassGenerator2();
                gen.addProperties(src, group);
                generators.add(gen);
            }
            sources.add(src);
        }
    }
}
