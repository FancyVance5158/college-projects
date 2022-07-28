using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TechnicianIncidentDisplay;

namespace IncidentsByTechnician
{
    public partial class frmIncidentsByTechnician : Form
    {
        public frmIncidentsByTechnician()
        {
            InitializeComponent();
        }

        private void frmIncidentsByTechnician_Load(object sender, EventArgs e)
        {
            List<Technician> technicianList = TechnicianDB.GetTechnicians();
            List<Incident> incidentList = IncidentDB.GetIncidents();

            var incidents = from incident in incidentList
                            join technician in technicianList
                            on incident.TechID equals technician.TechID
                            where incident.DateClosed != null
                            orderby technician.Name, incident.DateOpened ascending
                            select new
                            {
                                technician.Name,
                                incident.ProductCode,
                                incident.DateOpened,
                                incident.DateClosed,
                                incident.Title
                            };

            string technicianName = "";
            int i = 0;
            foreach (var incident in incidents)
            {
                if (incident.Name != technicianName)
                {
                    lvIncidents.Items.Add(incident.Name);
                    technicianName = incident.Name;
                }
                else
                {
                    lvIncidents.Items.Add("");
                }
                lvIncidents.Items[i].SubItems.Add(incident.ProductCode.ToString());
                lvIncidents.Items[i].SubItems.Add(Convert.ToDateTime(incident.DateOpened).ToShortDateString());
                lvIncidents.Items[i].SubItems.Add(Convert.ToDateTime(incident.DateClosed).ToShortDateString());
                lvIncidents.Items[i].SubItems.Add(incident.Title.ToString());
                i++;
            }
        }
    }
}
