   M S B A M L   `   `   `  ����B  >WpfApp2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null7 clr-namespace:WpfApp2.ValidacijeWpfApp2.Validacije  5   	   ,    &WpfApp2.Windows.AddEditProfessorWindow   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   7
validacije clr-namespace:WpfApp2.Validacije     5   	     � Title$  AddEditProfessorWindow��5   	   $	��450��6(   $	��800��65   .��5	      �  � ColumnDefinitions 5
   
   �� 5      �� 5       � RowDefinitions 5   
   �� 5      �� 5      �� 5      �� 5      .+�5   
   �� -   5        $��lblIme��5      $
��Ime:��6   $��0��6-   $��0��6=   6L   �� 5   
   -   5        $��txtIme��5      $��1��6    $��0��60   ��6=   ��  �� Path$	 Ime� �� ValidatesOnDataErrors . .��6s   5      �� 5   
   ��6   �� $) #(Validation.Errors)[0].ErrorContent�6P   �� 5   
   -   5        $��lblEmail��5      $��Email:��6    $��0��61   $��1��6A   6P   �� 5   
   -   5        $��txtEmail��5      $��1��6"   $��1��62   .��6?   ��5      �� 5      $ Email�6    �� ValidationRules 5      *   $WpfApp2.Validacije.EmailValidacijaca  5      5!      .��5"       =� ErrorTemplate 5#      �� 5$      .R�5%      �� .+�5&      �� �� 5'      
���  ����6$   �� *[0].ErrorContent+6Y   5(      5)      .��5*   (   5+      �� 5-   
   -   5        $��lblTip��5-      $��Tip korisnika:��6   $��0��67   $��2��6G   6V   �� 5.   
   -   5        $��cmbTipKorisnika��5.      $��1��6*   $��2��6:    �� SelectedItem 6G   �� $ TipKorisnika�5/      �� 51   
   -   5        $��btnOdustani��51      $��Odustani��6$   $��0��67   $��3��6G   6T   �� 52   
   -   5        $��
btnSacuvaj��52      $��Sacuvaj��6#   $��1��65   $��3��6E   6R   54      55      