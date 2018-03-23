package pub.androidrubick.autotest.core

import org.gradle.api.Project

/**
 * 当 apply 任何插件时，会尝试向{@link Project project}注入名为`atm`的对象；
 *
 * 包含一些基础操作、工具，比如日志，
 *
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
class ATMImpl extends ATM implements IATM {

    ATMImpl(Project project) {
        super(project)
    }

}
