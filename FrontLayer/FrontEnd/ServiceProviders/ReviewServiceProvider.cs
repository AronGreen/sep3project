using System.Collections.Generic;
using System.Text.Json;
using Data.Models.Entities;
using FrontEnd.Constants;
using FrontEnd.Helpers;
using Microsoft.AspNetCore.Mvc;

namespace FrontEnd.ServiceProviders
{
    public class ReviewServiceProvider : IReviewServiceProvider
    {
        public IList<Review> GetAllByReviewee(string email)
        {
            var response = ApiHelpers.DoGet(Constants.Api.Reviews.GetAllByReviewee + "/" + email);
            var json = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();
            return response.IsSuccessStatusCode ? JsonSerializer.Deserialize<List<Review>>(json) : null;
        }

        public bool Create(Review review, string token)
        {
            return ApiHelpers.DoPost(Api.Reviews.Create, review, token).IsSuccessStatusCode;
        }
    }
}