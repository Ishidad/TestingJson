package dragondreamstudio.beermap.json;

import com.android.volley.Request;

/**
 * Created by martin.villarruel on 6/27/2016.
 */

public abstract class RequestLoader {

    public Request getRequest(){
        Request request = generateRequest(generateUrl());
        return request;
    }

    protected abstract Request generateRequest(String url);
    protected abstract String generateUrl();
}
