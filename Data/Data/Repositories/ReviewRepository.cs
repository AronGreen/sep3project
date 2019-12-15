using Data.Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using Data.Models.Helpers;
using System.Text;

namespace Data.Repositories 
{
    class ReviewRepository : IReviewRepository
    {
        public Review Create(Review review)
        {
            using var context = new ApplicationContext();
            try
            {
                var result = context.Reviews.Add(review).Entity;
                context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Review Delete(int id)
        {
            using var context = new ApplicationContext();
            try
            {
                var result = context.Reviews.Single(x => x.Id == id);

                context.Reviews.Remove(result);
                context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Review[] GetAllByRevieweeEmail(string revieweeEmail)
        {
            using var context = new ApplicationContext();
            try
            {
                return context.Reviews
                    .Select(x => x)
                    .Where(x => x.RevieweeEmail == revieweeEmail)
                    .ToArray();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Review[] GetAllByReviewerEmail(string reviewerEmail)
        {
            using var context = new ApplicationContext();
            try
            {
                return context.Reviews
                    .Select(x => x)
                    .Where(x => x.ReviewerEmail == reviewerEmail)
                    .ToArray();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Review GetById(int id)
        {
            using var context = new ApplicationContext();
            try
            {
                return context.Reviews.Single(x => x.Id == id);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }
    }
}
