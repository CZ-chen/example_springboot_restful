package tech.nodex.example_springboot_restful.dao.utils;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import tech.nodex.example_springboot_restful.App;

import javax.sql.DataSource;
import java.io.File;


/**
 * 生成Active Record 代码。当DB发生变更时，执行该类来更新Active Record Model。
 * Created by cz on 2017-2-6.
 */
public class ActiveRecordGenerator {
    public static void main(String[] args) {
        //使用项目的数据源作为代码生成的数据源
        App app = new App();
        DataSource dataSource = app.activeRecord().getDataSource();

        //计算代码根目录:
        File codeBase = new File(new File(PathKit.getRootClassPath()).getParentFile().getParentFile(),"src/main/java");

        //把生成的model类放在本类所在的上层包下:
        String modelPkg = ActiveRecordGenerator.class.getPackage().getName().replace(".utils","");
        //定义model的基础类所在包:
        String baseModelPkg = modelPkg+".base";

        //开始生成代码:
        gen(dataSource,codeBase,modelPkg,baseModelPkg);
    }

    /**
     *  生成Active Record 代码
     * @param dataSource - 指定要连接的数据源
     * @param codeBase - 代码根目录
     * @param modelPkg - model包名
     * @param baseModelPkg - model基础类包名
     */
    private static void gen(DataSource dataSource,File codeBase, String modelPkg, String baseModelPkg){
        String baseModelDir = new File(codeBase,baseModelPkg.replaceAll("\\.","/")).getPath();
        String modelDir = new File(codeBase,modelPkg.replaceAll("\\.","/")).getPath();

        Generator gernerator = new Generator(dataSource, baseModelPkg, baseModelDir, modelPkg, modelDir);
        gernerator.generate();
    }
}
