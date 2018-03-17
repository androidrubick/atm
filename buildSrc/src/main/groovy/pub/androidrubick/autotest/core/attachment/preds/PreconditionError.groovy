package pub.androidrubick.autotest.core.attachment.preds

@SuppressWarnings("GroovyUnusedDeclaration")
class PreconditionError extends RuntimeException {

    PreconditionError() {
    }

    PreconditionError(String message) {
        super(message)
    }

    PreconditionError(String message, Throwable cause) {
        super(message, cause)
    }

    PreconditionError(Throwable cause) {
        super(cause)
    }

}