
package functions
import com.google.cloud.functions.HttpFunction
import com.google.cloud.functions.HttpRequest
import com.google.cloud.functions.HttpResponse

class Hello : HttpFunction {
    override fun service(request: HttpRequest, response: HttpResponse) {
        response.writer.write("Hello World!")
    }

}

