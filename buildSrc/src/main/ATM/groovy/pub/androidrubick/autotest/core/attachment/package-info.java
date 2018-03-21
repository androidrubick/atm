/**
 * "附件（attachment）"，即附着在某些对象上的东西.
 *
 * 比如：
 *
 * 我们会在{@link pub.androidrubick.autotest.core.ATM atm}中加入命令行相关的功能，
 * 就将命令行工具——cmd作为一个attachment放入到{@code atm}，
 * 而每个attachment在运行时又能访问到{@code atm}中的其他功能（"附件（attachment）"）;
 *
 * @since 1.0.0
 */
package pub.androidrubick.autotest.core.attachment;