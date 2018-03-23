package pub.androidrubick.autotest.core.attachment.cmd

import pub.androidrubick.autotest.core.attachment.BaseAttachment

import java.util.concurrent.TimeUnit

@SuppressWarnings("GroovyUnusedDeclaration")
public class ExecProcBuilder extends BaseAttachment {
    private Object commands
    private long timeout = 15
    private TimeUnit timeoutUnit = TimeUnit.SECONDS
    private Object env
    private Object dir

    ExecProcBuilder(context, String... commands) {
        super(context)
        this.commands = commands
    }

    public static ExecProcBuilder by(context, String... commands) {
        return new ExecProcBuilder(context, commands)
    }

    public ExecProcBuilder timeout(long timeout, TimeUnit unit = TimeUnit.SECONDS) {
        if (timeout > 0 && unit != null) {
            this.timeout = timeout
            this.timeoutUnit = unit
        }
        return this
    }

    public ExecProcBuilder noTimeout() {
        this.timeout = 0
        this.timeoutUnit = null
        return this
    }

    public ExecProcBuilder env(env) {
        this.env = env
        return this
    }

    public ExecProcBuilder dir(dir) {
        this.dir = dir
        return this
    }

    public ExecResult build() {
        def innerCmds = ['sh', '-c']
        innerCmds.addAll(this.commands)
        def proc
        if (env != null || dir != null) {
            proc = innerCmds.execute(env, dir)
        } else {
            proc = innerCmds.execute()
        }
        def sout = new StringBuilder()
        def serr = new StringBuilder()

        proc.consumeProcessOutput(sout, serr)
        if (timeout > 0) {
            proc.waitFor(timeout, timeoutUnit)
        } else {
            proc.waitFor()
        }

        return new ExecResult(this.context, proc.exitValue(), sout.toString(), serr.toString())
    }

    public ExecResult exec() {
        logBefore()
        def result = build()
        logAfter(result)
        return result
    }

    private void logBefore() {
        if (this.context) {
            atm.log('>>>>>>>>>>>>>>>>>>>>>>>>>Process print start<<<<<<<<<<<<<<<<<<<<<<<<<')
            this.commands.each { command ->
                atm.log("> $command")
            }
        }
    }

    private void logAfter(result) {
        if (this.context) {
            atm.log("Process exit value: ${result.code}")
            atm.log("Process out: ${result.text ?: '-'}")
            atm.log("Process err: ${result.err ?: '-'}")
            atm.log('>>>>>>>>>>>>>>>>>>>>>>>>> Process print end <<<<<<<<<<<<<<<<<<<<<<<<<\n')
        }
    }

    @Override
    public String toString() {
        return "ExecProcBuilder{" +
                "commands=" + commands +
                '}'
    }
}
