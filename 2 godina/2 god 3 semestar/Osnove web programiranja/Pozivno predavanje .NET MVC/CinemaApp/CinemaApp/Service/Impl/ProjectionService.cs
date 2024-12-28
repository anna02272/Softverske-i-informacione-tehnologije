﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CinemaApp.Dao;
using CinemaApp.Models;

namespace CinemaApp.Service.Impl
{
    public class ProjectionService : IProjectionService
    {
        private IProjectionDAO _projectionDAO;

        public ProjectionService(IProjectionDAO projectionDAO)
        {
            _projectionDAO = projectionDAO;
        }

        public Projection Delete(long id)
        {
            throw new NotImplementedException();
        }

        public List<Projection> Find(DateTime dateFrom, DateTime dateTo, long movieId, string projectionType, string hall, double minPriceTicket, double maxPriceTicket)
        {
            throw new NotImplementedException();
        }

        public List<Projection> FindAll()
        {
            throw new NotImplementedException();
        }

        public Projection FindOne(long id)
        {
            throw new NotImplementedException();
        }

        public Projection Save(Projection projection)
        {
            throw new NotImplementedException();
        }

        public List<Projection> Save(List<Projection> projections)
        {
            throw new NotImplementedException();
        }

        public Projection Update(Projection user)
        {
            throw new NotImplementedException();
        }
    }
}