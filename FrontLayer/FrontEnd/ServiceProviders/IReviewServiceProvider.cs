using System.Collections.Generic;
using System.Reflection.Metadata.Ecma335;
using Data.Models.Entities;

namespace FrontEnd.ServiceProviders
{
    public interface IReviewServiceProvider
    {

        IList<Review> GetAllByReviewee(string email);
        bool Create(Review review, string token);

    }
}