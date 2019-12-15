using Data.Models.Entities;

namespace Data.Repositories
{
    public interface IReviewRepository
    {

        Review Create(Review review);
        Review GetById(int id);
        Review[] GetAllByReviewerEmail(string reviewerEmail);
        Review[] GetAllByRevieweeEmail(string revieweeEmail);
        Review Delete(int id);

    }
}
