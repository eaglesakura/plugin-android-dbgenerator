package com.eaglesakura.gradle.plugin;

import org.gradle.testfixtures.ProjectBuilder;

import java.io.File;

import groovy.util.GroovyTestCase;

import static org.junit.Assert.*;

/**
 * プロパティ出力のテスト
 */
public class AndroidPropertiesPluginTest extends GroovyTestCase {

    public void test_Applyが成功する() {
        def project = ProjectBuilder.builder().build();
        project.apply plugin: AndroidPropertiesPlugin

        project.props.output(new File("src/test/gen"));

        project.tasks.androidGenerateProperties.execute();
    }

    public void test_JSONからプロパティ出力が成功する() {
        def project = ProjectBuilder.builder().build();
        project.apply plugin: AndroidPropertiesPlugin

        project.props.output(new File("src/test/gen"));
        project.props.property(new File("src/test/assets/properties.json"), "com.example.unit.test")

        project.tasks.androidGenerateProperties.execute();
    }
}