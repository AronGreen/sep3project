using System;
using System.Collections.Generic;
using System.Text;
using System.Text.Json;
using Data.Models.Entities;
using Data.Network;
using Data.Repositories;


namespace Data.Logic.RequestTables
{
    public class ReviewRequestTableComposer : IRequestTableComposer
    {

        private readonly IReviewRepository _reviewRepository;

        public ReviewRequestTableComposer(IReviewRepository reviewRepository) {
            _reviewRepository = reviewRepository;
        }
        public void Compose(IDictionary<(string, string), Handler> map)
        {
            map.Add(("review", "create"), CreateReview());
            map.Add(("review", "delete"), DeleteReview());
            map.Add(("review", "getAllByRevieweeEmail"), GetAllByRevieweeEmail());
            map.Add(("review", "getAllByReviewerEmail"), GetAllByReviewerEmail());
            map.Add(("review", "getById"), GetById());
        }

        private Handler CreateReview() => body =>
        {
            var result = _reviewRepository.Create(JsonSerializer.Deserialize<Review>(body));
            var status = result == null ? "internalError" : "success";
            return new Response
            {
                Status = status,
                Body = result
            };
        };

        private Handler DeleteReview() => body =>
        {
            var result = _reviewRepository.Delete(int.Parse(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetAllByRevieweeEmail() => body =>
        {
            var result = _reviewRepository.GetAllByRevieweeEmail(body);
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetAllByReviewerEmail() => body =>
        {
            var result = _reviewRepository.GetAllByReviewerEmail(body);
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };
        private Handler GetById() => body =>
        {
            var result = _reviewRepository.GetById(int.Parse(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };
    }
}
