using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.EntityFrameworkCore;

namespace Data.Data.Contexts
{

    /// <summary>
    /// The purpose of this context is to specify the database type for all the contexts,
    /// so there's no need to override the method in every context
    /// </summary>
    public abstract class BaseContext : DbContext
    {


        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            builder.UseInMemoryDatabase("db");

            base.OnConfiguring(builder);
        }

    }
}
