package pub.androidrubick.autotest.core.attachment.preds

import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment
import pub.androidrubick.autotest.core.util.Utils

@SuppressWarnings(["GroovyUnusedDeclaration", "GrMethodMayBeStatic"])
public class PreconditionUtil extends BaseAttachment {

    PreconditionUtil(ATMContext context) {
        super(context)
    }

    public Object property(String name, String label = '') {
        if (project.hasProperty(name)) {
            return project.property(name)
        }
        throw new PreconditionError("$label: Property `$name` Not Found")
    }

    public <T>T nonNull(T obj, String label = '') {
        if (obj == null) {
            throw new NullPointerException("$label: null")
        }
        return obj
    }

    public void isTrue(boolean cond, String label = '') {
        if (!cond) {
            throw new PreconditionError("$label: not expected true")
        }
    }

    public <T>T nonEmpty(T obj, String label = '') {
        if (Utils.isEmpty(obj)) {
            throw new PreconditionError("$label: empty")
        }
        return obj
    }

    public File file(File target, String label = '') {
        this.nonNull(target, label)
        if (!target.exists()) {
            throw new PreconditionError("$label: $target unexists")
        }
        return target
    }

    public File isFile(File target, String label = '') {
        this.file(target, label)
        if (!target.isFile()) {
            throw new PreconditionError("$label: $target is Not a File")
        }
        return target
    }

    public File isDir(File target, String label = '') {
        this.file(target, label)
        if (!target.isDirectory()) {
            throw new PreconditionError("$label: $target is Not a Dir")
        }
        return target
    }

}