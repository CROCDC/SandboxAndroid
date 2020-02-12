package com.cr.o.cdc.daggerutilbuilder;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J$\u0010\b\u001a\u00020\t2\u0010\u0010\n\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000b\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016\u00a8\u0006\u000f"}, d2 = {"Lcom/cr/o/cdc/daggerutilbuilder/FileGenerator;", "Ljavax/annotation/processing/AbstractProcessor;", "()V", "getSupportedAnnotationTypes", "", "", "getSupportedSourceVersion", "Ljavax/lang/model/SourceVersion;", "process", "", "set", "Ljavax/lang/model/element/TypeElement;", "roundEnvironment", "Ljavax/annotation/processing/RoundEnvironment;", "Companion", "daggerutilbuilder"})
@javax.annotation.processing.SupportedOptions(value = {"kapt.kotlin.generated"})
@javax.annotation.processing.SupportedSourceVersion(value = javax.lang.model.SourceVersion.RELEASE_8)
@com.google.auto.service.AutoService(value = javax.annotation.processing.Processor.class)
public final class FileGenerator extends javax.annotation.processing.AbstractProcessor {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated";
    public static final com.cr.o.cdc.daggerutilbuilder.FileGenerator.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.Set<java.lang.String> getSupportedAnnotationTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public javax.lang.model.SourceVersion getSupportedSourceVersion() {
        return null;
    }
    
    @java.lang.Override()
    public boolean process(@org.jetbrains.annotations.Nullable()
    java.util.Set<? extends javax.lang.model.element.TypeElement> set, @org.jetbrains.annotations.Nullable()
    javax.annotation.processing.RoundEnvironment roundEnvironment) {
        return false;
    }
    
    public FileGenerator() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/cr/o/cdc/daggerutilbuilder/FileGenerator$Companion;", "", "()V", "KAPT_KOTLIN_GENERATED_OPTION_NAME", "", "daggerutilbuilder"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}