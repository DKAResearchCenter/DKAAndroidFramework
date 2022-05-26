package com.dkaresearchcenter.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.dkaresearchcenter.dkaframework.DKA
import com.dkaresearchcenter.dkaframework.R
import com.dkaresearchcenter.dkaframework.databinding.UiLayoutFunctionAuthManagescreenAccountPanelAccountswitcherDialogBinding
import com.dkaresearchcenter.dkaframework.databinding.UiLayoutFunctionAuthManagescreenAccountPanelAccountswitcherDialogRecyclerviewBinding

internal class DKAAuthAccountPanelAccountSwitcherDialog(var activity : Activity) : DialogFragment() {

    private lateinit var mBinding : UiLayoutFunctionAuthManagescreenAccountPanelAccountswitcherDialogBinding
    private var am : AccountManager = AccountManager.get(activity)
    private var accountList : MutableList<Account> = mutableListOf()

    private lateinit var mAdapter : DKAAuthAccountDialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountList = am.getAccountsByType(activity.resources.getString(R.string.dka_account_type)).toMutableList()

        mBinding = UiLayoutFunctionAuthManagescreenAccountPanelAccountswitcherDialogBinding.inflate(LayoutInflater.from(activity))

        mAdapter = DKAAuthAccountDialogAdapter()
        mBinding.mRecyclerAccount.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }

        mBinding.mAddAccount.setOnClickListener {
            DKA.Auth()
                    .addApplicationName(activity.applicationInfo.loadLabel(activity.packageManager).toString())
                    .build()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mAdapter.notifyDataSetChanged()
        return MaterialAlertDialogBuilder(activity)
                .setTitle("Pilih Akun")
                .setView(mBinding.root)
                .create()
    }

    override fun onStart() {
        super.onStart()
        mAdapter.notifyDataSetChanged()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

    private inner class DKAAuthAccountDialogAdapter : RecyclerView.Adapter<DKAAuthAccountDialogAdapter.MyViewHolder>() {

        private lateinit var mBinding : UiLayoutFunctionAuthManagescreenAccountPanelAccountswitcherDialogRecyclerviewBinding

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            mBinding = UiLayoutFunctionAuthManagescreenAccountPanelAccountswitcherDialogRecyclerviewBinding.inflate(LayoutInflater.from(context), parent, false)

            return MyViewHolder(mBinding)
        }

        override fun getItemCount(): Int = accountList.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.UiBinding.mEmailProfileAccount.text = accountList[position].name
            holder.UiBinding.mContainerAccountSelection.setOnClickListener {
                this@DKAAuthAccountPanelAccountSwitcherDialog.dismiss()
            }

        }

        private inner class MyViewHolder(itemView: UiLayoutFunctionAuthManagescreenAccountPanelAccountswitcherDialogRecyclerviewBinding) : RecyclerView.ViewHolder(itemView.root) {
            val UiBinding = itemView
        }

    }
}