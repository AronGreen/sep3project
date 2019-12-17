using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net.Mime;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace FrontEnd.Helpers
{
    public static class ApiHelpers
    {
        public static HttpResponseMessage DoPost(string endpoint, object payload, AuthenticationHeaderValue authenticationHeader = null)
        {
            using var client = new HttpClient();

            if (authenticationHeader != null)
            {
                client.DefaultRequestHeaders.Authorization = authenticationHeader;
            }

            var content = payload == null 
                ? new StringContent("") 
                : new StringContent(
                    JsonSerializer.Serialize(payload),
                    Encoding.UTF8,
                    MediaTypeNames.Application.Json);

            return client.PostAsync(endpoint, content).GetAwaiter().GetResult();
        }

        public static HttpResponseMessage DoPost(string endpoint, object payload, string token)
        {
            using var client = new HttpClient();

            var json = JsonSerializer.Serialize(payload);
            var content = new StringContent(json, Encoding.UTF8, MediaTypeNames.Application.Json);

            var authenticationToken = Convert.ToBase64String(Encoding.UTF8.GetBytes($"{token}" + ":"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authenticationToken);

            return client.PostAsync(endpoint, content).GetAwaiter().GetResult();
        }

        public static HttpResponseMessage DoGet(string endpoint, string token)
        {
            using var client = new HttpClient();

            var authenticationToken = Convert.ToBase64String(Encoding.UTF8.GetBytes($"{token}" + ":"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authenticationToken);

            return client.GetAsync(endpoint).GetAwaiter().GetResult();
        }

        public static HttpResponseMessage DoGet(string endpoint)
        {
            using var client = new HttpClient();
            return client.GetAsync(endpoint).GetAwaiter().GetResult();
        }
    }
}
