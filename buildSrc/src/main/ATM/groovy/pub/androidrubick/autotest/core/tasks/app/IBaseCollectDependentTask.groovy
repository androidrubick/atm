package pub.androidrubick.autotest.core.tasks.app

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public interface IBaseCollectDependentTask<AC extends ArchiveCollector> {

    public void setArchiveCollector(AC archiveCollector)

    public AC getArchiveCollector()

}