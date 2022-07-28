namespace IncidentsByTechnician
{
    partial class frmIncidentsByTechnician
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.lvIncidents = new System.Windows.Forms.ListView();
            this.colTechnician = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colProduct = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colDateOpened = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colDateClosed = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colTitle = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.SuspendLayout();
            // 
            // lvIncidents
            // 
            this.lvIncidents.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colTechnician,
            this.colProduct,
            this.colDateOpened,
            this.colDateClosed,
            this.colTitle});
            this.lvIncidents.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.lvIncidents.HideSelection = false;
            this.lvIncidents.Location = new System.Drawing.Point(12, 12);
            this.lvIncidents.Name = "lvIncidents";
            this.lvIncidents.Size = new System.Drawing.Size(1011, 426);
            this.lvIncidents.TabIndex = 0;
            this.lvIncidents.UseCompatibleStateImageBehavior = false;
            this.lvIncidents.View = System.Windows.Forms.View.Details;
            // 
            // colTechnician
            // 
            this.colTechnician.Text = "Technician";
            this.colTechnician.Width = 150;
            // 
            // colProduct
            // 
            this.colProduct.Text = "Product";
            this.colProduct.Width = 90;
            // 
            // colDateOpened
            // 
            this.colDateOpened.Text = "Date Opened";
            this.colDateOpened.Width = 90;
            // 
            // colDateClosed
            // 
            this.colDateClosed.Text = "Date Closed";
            this.colDateClosed.Width = 90;
            // 
            // colTitle
            // 
            this.colTitle.Text = "Title";
            this.colTitle.Width = 250;
            // 
            // frmIncidentsByTechnician
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1041, 450);
            this.Controls.Add(this.lvIncidents);
            this.Name = "frmIncidentsByTechnician";
            this.Text = "Incidents By Technician";
            this.Load += new System.EventHandler(this.frmIncidentsByTechnician_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListView lvIncidents;
        private System.Windows.Forms.ColumnHeader colTechnician;
        private System.Windows.Forms.ColumnHeader colProduct;
        private System.Windows.Forms.ColumnHeader colDateOpened;
        private System.Windows.Forms.ColumnHeader colDateClosed;
        private System.Windows.Forms.ColumnHeader colTitle;
    }
}

