/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repo.ebook.plugin.config;

import java.io.IOException;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

import repo.ebook.plugin.analyzer.SerbianAnalyzer;




/**
 *
 * @author Milan Deket
 */
 
 /**
 * To set Analyzer on indexed entity field add annotation
 * @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true, analyzer="serbian_analyzer")
 */
public class SerbianAnalyzerProvider extends AbstractIndexAnalyzerProvider{

    @Inject
    public SerbianAnalyzerProvider(Index index,  Settings indexSettings, @Assisted String name, @Assisted Settings settings) throws IOException {
        super(index, indexSettings, name, settings);
    }

    public SerbianAnalyzer get() {
        return this.analyzer;
    }
    
    protected SerbianAnalyzer analyzer = new SerbianAnalyzer();
    public static final String NAME = "serbian_analyzer";}
