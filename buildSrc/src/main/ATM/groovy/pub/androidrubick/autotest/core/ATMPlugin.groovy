package pub.androidrubick.autotest.core

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.tasks.app.ArchiveCollector
import pub.androidrubick.autotest.core.tasks.app.DefaultArchiveCollector
import pub.androidrubick.autotest.core.tasks.upload.Upload2PgyerTask
import pub.androidrubick.autotest.core.util.Utils

/**
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "WeakerAccess"])
public class ATMPlugin extends BaseATMPlugin {

    @Override
    protected void applyMe(@NonNull Project project, @NonNull ATM atm) {
        AppArchiveType.allAvailable.collect { AppArchiveType type ->
            new DefaultArchiveCollector(atm.context, type)
        }.each { DefaultArchiveCollector ac ->
            ac.createDefaultCollectAppTask()
        }.each { ArchiveCollector ac ->
            project.with {
                String capitalizedTypeName = Utils.capitalize(ac.type.name)

                def collectTaskName = ac.collectAppTask.name
                def uploadTaskName = 'upload' + capitalizedTypeName + '2Pgyer'
                tasks.create(uploadTaskName, Upload2PgyerTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(collectTaskName)
            }
        }
    }

}
