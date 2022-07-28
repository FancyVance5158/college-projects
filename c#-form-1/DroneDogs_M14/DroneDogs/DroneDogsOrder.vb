Public Class DroneDogsOrder

    Private Sub btnCalculate_Click(sender As Object, e As EventArgs) Handles btnCalculate.Click
        'Code for the Calculate Order button
        'Takes in and validates the numbers of each type of hot dog,
        'Then calculates the subtotal, sales tax and total cost.

        'Holds a numeric version of each variable in order to calculate using them.
        Dim numBeef, numPork, numTurkey, totDogs As Integer
        Dim subtotal, salesTaxAmt, totalCost As Double

        'Initializes the constants used to calculate subtotal and sales tax.
        Const COST_PER_DOG As Double = 1.99
        Const SALES_TAX_RATE As Double = 0.06

        'Here the input strings are converted to integers.
        'If the input is a decimal or string, the function fails and an error will occur.
        Try
            numBeef = Convert.ToInt32(txtBeefDogs.Text)
            numPork = Convert.ToInt32(txtPorkDogs.Text)
            numTurkey = Convert.ToInt32(txtTurkeyDogs.Text)
        Catch ex As Exception
            MessageBox.Show("ERROR...Please enter a whole number. Words or letters are not valid.")

            'Resets the input boxes.
            txtBeefDogs.Text = ""
            txtPorkDogs.Text = ""
            txtTurkeyDogs.Text = ""

            'This line exits the rest of the code for the Calculate button, skipping the actual calculation.
            Exit Sub
        End Try

        'If the user enters 0 for each field a message will pop up.
        If numBeef = 0 And numPork = 0 And numTurkey = 0 Then
            MessageBox.Show("ERROR...You must order at least one item.")
            txtBeefDogs.Text = ""
            txtPorkDogs.Text = ""
            txtTurkeyDogs.Text = ""
            Exit Sub

            'This one catches negative numbers.
        ElseIf numBeef < 0 Or numPork < 0 Or numTurkey < 0 Then
            MessageBox.Show("ERROR...Please enter a positive number.")
            txtBeefDogs.Text = ""
            txtPorkDogs.Text = ""
            txtTurkeyDogs.Text = ""
            Exit Sub
        End If

        'The calculations are performed here.
        totDogs = numBeef + numPork + numTurkey
        subtotal = totDogs * COST_PER_DOG
        salesTaxAmt = subtotal * SALES_TAX_RATE
        totalCost = subtotal - salesTaxAmt

        'The results are displayed in the output boxes.
        txtSubtotal.Text = subtotal.ToString("c2")
        txtSalesTax.Text = salesTaxAmt.ToString("c2")
        txtTotalCost.Text = totalCost.ToString("c2")
    End Sub

    Private Sub btnExit_Click(sender As Object, e As EventArgs) Handles btnExit.Click
        'Close the form
        MessageBox.Show("Thank you for ordering from Drone Dogs!")
        Me.Close()
    End Sub


    Private Sub btnCustomer_Click(sender As Object, e As EventArgs) Handles btnCustomer.Click
        'Make the customer form visible
        CustomerForm.Show()
    End Sub


    Private Sub btnClear_Click(sender As Object, e As EventArgs) Handles btnClear.Click
        'Clear all text fields
        txtBeefDogs.Text = ""
        txtPorkDogs.Text = ""
        txtTurkeyDogs.Text = ""
        txtFirstName.Text = ""
        txtLastName.Text = ""
        txtEmail.Text = ""
        txtSubtotal.Text = ""
        txtSalesTax.Text = ""
        txtTotalCost.Text = ""
    End Sub

    Private Sub btnSubmit_Click(sender As Object, e As EventArgs) Handles btnSubmit.Click
        'Check the permission check box, the total cost text box and the email text box
        'Display an error message if any of them are empty
        'Otherwise, display a message box thanking them for ordering
        If chkPermission.Checked = False Then
            MessageBox.Show("ERROR...You must check the location permission check box.")
        ElseIf txtTotalCost.Text = "" Then
            MessageBox.Show("ERROR...You must order at least one item.")
        ElseIf txtEmail.Text = "" Then
            MessageBox.Show("ERROR...Please get customer information for this order.")
        Else
            MessageBox.Show("Thank you for ordering from DroneDogs!")
        End If

    End Sub

End Class