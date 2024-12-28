﻿using CinemaApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CinemaApp.Service
{
    public interface IProjectionService
    {
        Projection FindOne(long id);
        List<Projection> FindAll();
        Projection Save(Projection projection);
        List<Projection> Save(List<Projection> projections);
        Projection Update(Projection user);
        Projection Delete(long id);
        List<Projection> Find(DateTime dateFrom, DateTime dateTo, long movieId, string projectionType, string hall, double minPriceTicket, double maxPriceTicket);
    }
}
