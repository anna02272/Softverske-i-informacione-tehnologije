﻿#pragma checksum "..\..\..\Windows\AllProfessorsWindow.xaml" "{8829d00f-11b8-4213-878b-770e8597ac16}" "FC5294472C5D236FCC6115D6E6B53F1E5AEC54D212E8D48FD318B683907AF2CA"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;
using WpfApp2.Windows;


namespace WpfApp2.Windows {
    
    
    /// <summary>
    /// AllProfessorsWindow
    /// </summary>
    public partial class AllProfessorsWindow : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 11 "..\..\..\Windows\AllProfessorsWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.MenuItem miDodajProfesora;
        
        #line default
        #line hidden
        
        
        #line 12 "..\..\..\Windows\AllProfessorsWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.MenuItem miIzmeniProfesora;
        
        #line default
        #line hidden
        
        
        #line 13 "..\..\..\Windows\AllProfessorsWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.MenuItem miObrisiProfesora;
        
        #line default
        #line hidden
        
        
        #line 15 "..\..\..\Windows\AllProfessorsWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.DataGrid dgProfesori;
        
        #line default
        #line hidden
        
        
        #line 16 "..\..\..\Windows\AllProfessorsWindow.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox txtPretraga;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/WpfApp2;component/windows/allprofessorswindow.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\..\Windows\AllProfessorsWindow.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            this.miDodajProfesora = ((System.Windows.Controls.MenuItem)(target));
            
            #line 11 "..\..\..\Windows\AllProfessorsWindow.xaml"
            this.miDodajProfesora.Click += new System.Windows.RoutedEventHandler(this.miDodajProfesora_Click);
            
            #line default
            #line hidden
            return;
            case 2:
            this.miIzmeniProfesora = ((System.Windows.Controls.MenuItem)(target));
            
            #line 12 "..\..\..\Windows\AllProfessorsWindow.xaml"
            this.miIzmeniProfesora.Click += new System.Windows.RoutedEventHandler(this.miIzmeniProfesora_Click);
            
            #line default
            #line hidden
            return;
            case 3:
            this.miObrisiProfesora = ((System.Windows.Controls.MenuItem)(target));
            
            #line 13 "..\..\..\Windows\AllProfessorsWindow.xaml"
            this.miObrisiProfesora.Click += new System.Windows.RoutedEventHandler(this.miObrisiProfesora_Click);
            
            #line default
            #line hidden
            return;
            case 4:
            this.dgProfesori = ((System.Windows.Controls.DataGrid)(target));
            
            #line 15 "..\..\..\Windows\AllProfessorsWindow.xaml"
            this.dgProfesori.AutoGeneratingColumn += new System.EventHandler<System.Windows.Controls.DataGridAutoGeneratingColumnEventArgs>(this.dgProfesori_AutoGeneratingColumn);
            
            #line default
            #line hidden
            return;
            case 5:
            this.txtPretraga = ((System.Windows.Controls.TextBox)(target));
            
            #line 16 "..\..\..\Windows\AllProfessorsWindow.xaml"
            this.txtPretraga.KeyUp += new System.Windows.Input.KeyEventHandler(this.txtPretraga_KeyUp);
            
            #line default
            #line hidden
            return;
            }
            this._contentLoaded = true;
        }
    }
}

