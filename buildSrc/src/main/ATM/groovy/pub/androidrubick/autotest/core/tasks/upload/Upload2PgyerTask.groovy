package pub.androidrubick.autotest.core.tasks.upload

import groovy.json.JsonSlurper

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings(["UnnecessaryQualifiedReference", "GroovyUnusedDeclaration"])
public class Upload2PgyerTask extends BaseUploadTask {

    @Override
    protected void upload(Object archiveParams) {
        def pgyerParams = checkPgyerParams()

        def pgyerCurl = []
        pgyerCurl << 'curl'
        pgyerCurl << "-F \"file=@${archiveParams.path}\""
        pgyerCurl << "-F \"uKey=${pgyerParams.userKey}\""
        pgyerCurl << "-F \"_api_key=${pgyerParams.apiKey}\""
        pgyerCurl << "-F \"updateDescription=${pgyerParams.updateDesc}\""
        pgyerCurl << pgyerParams.url
        def command = pgyerCurl.join(' ')

        atm.log("command is $command")

        def result = atm.cmd.execn(command)
        if (result.code != 0) {
            throw new RuntimeException("Upload2Pgyer Execute curl error\nDetails: ${result.err}")
        }

        if (isEmpty(result.text)) {
            throw new RuntimeException("Upload2Pgyer Execute curl error\nDetails: Curl No result Returned!")
        }

        def pgyerResult = parsePgyerResult(result.text)
        if (pgyerResult.code != 0 || pgyerResult.data == null) {
            throw new RuntimeException("Upload2Pgyer Execute curl error\nDetails: Curl result: $pgyerResult")
        }

        atm.log("Upload2Pgyer Result Data is: ${pgyerResult.data}")
    }

    private checkPgyerParams() {
        def pgyer = [:]
        def pgyerUserKey = atm.prop.value('PGYER_UKEY', null)
        if (isEmpty(pgyerUserKey)) {
            pgyerUserKey = '93886ded277bd08b288c8a6b5f25dac8'
        }
        pgyer.userKey = pgyerUserKey

        def pgyerApiKey = atm.prop.value('PGYER_API_KEY', null)
        if (isEmpty(pgyerApiKey)) {
            pgyerApiKey = '917d1e20fc7c64d70c5cd2dc5655701f'
        }
        pgyer.apiKey = pgyerApiKey

        def pgyerUpdateDesc = atm.prop.value('PGYER_UPDATE_DESC', null)
        if (isEmpty(pgyerUpdateDesc)) {
            pgyerUpdateDesc = 'Uploaded By Jenkins'
        }
        pgyer.updateDesc = pgyerUpdateDesc

        def pgyerUrl = atm.prop.value('PGYER_URL', null)
        if (isEmpty(pgyerUrl)) {
            pgyerUrl = 'https://qiniu-storage.pgyer.com/apiv1/app/upload'
        }
        pgyer.url = pgyerUrl
        return pgyer
    }

    private static parsePgyerResult(String jsonText = null) {
        try {
            def jsonSlurper = new JsonSlurper()
            def object = jsonSlurper.parseText(jsonText)
            return object
        } catch (Throwable e) {
            throw new RuntimeException("Upload2Pgyer Execute curl error\nDetails: Result Json is Invalid!", e)
        }
    }
}