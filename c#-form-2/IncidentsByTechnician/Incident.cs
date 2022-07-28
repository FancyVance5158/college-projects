using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IncidentsByTechnician
{
    public class Incident
    {
        public int CustomerID { get; set; }
        public DateTime? DateClosed { get; set; }
        public DateTime DateOpened { get; set; }
        public String Description { get; set; }
        public int IncidentID { get; set; }
        public String ProductCode { get; set; }
        public int? TechID { get; set; }
        public String Title { get; set; }
    }
}
