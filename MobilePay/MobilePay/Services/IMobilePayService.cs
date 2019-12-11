namespace MobilePay.Services
{
    public interface IMobilePayService
    {

        /*
         * Step 1: Initiate consent
         *  - Call a GET request (authorize), and by passing the right parameters, it returns a consent screen.
         *  - After consent is granted, the user is redirected to the redirect URI
         * Step 2: Get the code
         *  - Redirect URI parameters:
         *      - a code that can be exchanged for an access token, the code is valid for 5 minutes only
         *      - a state parameter that needs to be verified (security that we aren't getting junk)
         * Step 3: Get the token
         *  - POST request to the token endpoint (with the code received in Step 2)
         *  - The response body contains:
         *      - id token
         *      - access token
         *      - refresh token
         * Step 4: Refresh token
         *  - (scope: offline_access must have been requested)
         *  - POST request to the token endpoint
         *  - Response contains:
         *      - access token
         *      - refresh token
         *
         * What the MobilePay server needs to do
         *  - Get the code that comes to the redirect URI (once ever); fuck the state
         *  - Make a post request to exchange it to tokens
         *  - Get the id, access, and refresh tokens on the redirect URI
         *  - Call the API. If the tokens are expired, request new tokens with the refresh token.
         */

        string GetConsentUrl();  // Step 1
        string RequestTokens(string code, string state, string idToken); // Step 2-3
        void SetTokens(string idToken, string accessToken, string refreshToken, int expiresIn);
        void SendInvoice();

        string GetTokens();
        string GetCode();
        string GetCodeVerifier();
        string GetCodeChallenge();

    }
}